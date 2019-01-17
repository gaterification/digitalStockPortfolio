package DigitalStockPortfolio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JobWorker {
	// class attributes
	private static JobWorker jobWorker;

	// attributes
	private ArrayList<Job> jobs;
	private StockExchange stockExchange;
	private CustodyAccount custodyAccount;
	private TimerTask periodicalRunJobs;

	// construct
	protected JobWorker(StockExchange stockExchange, CustodyAccount custodyAccount) {
		this.jobs = new ArrayList<Job>();
		this.stockExchange = stockExchange;
		this.custodyAccount = custodyAccount;
		this.initializePeriodicalRunJobs();
	}

	protected void removeJob(int id) {
		this.jobs.removeIf(e -> e.getId() == id);
	}

	protected void addJob(Job job) {
		this.jobs.add(job);
	}

	protected void runJobs() throws StockExchangeException, JobWorkerException {
		System.out.println("Log: Offene Jobs werden abgearbeitet.");
		if (this.jobs.size() == 0) {
			System.out.println("Log: JobWorker-Info: Es gibt keine Jobs abzuarbeiten.");
		} else {
			// sort job: first all job with type SELL should be executed so there is more money to buy jobs
			this.jobs.sort(new Comparator<Job>() {
				@Override
				public int compare(Job j1, Job j2) {
					if (j1.getJobType() == j2.getJobType()) {
						return 0;
					} else if (j1.getJobType() == JobType.BUY) {
						return 1;
					} else {
						return -1;
					}
				}
			});

			// copy this.jobs because jobs are removed from this.jobs during loop
			ArrayList<Job> jobsForIteration = new ArrayList<Job>(this.jobs); 
			try {
				for (Job job : jobsForIteration) {
					if (job.getJobType() == JobType.BUY) {
						this.handleJobTypeBuy(job);
					} else {
						this.handleJobTypeSell(job);				
					}
				}
			} catch (StockExchangeException | JobWorkerException e) {
				throw e;
			}
		}
	}

	protected ArrayList<Job> getJobs() {
		return this.jobs;
	}

	private void initializePeriodicalRunJobs() {
		// initialize timer
		this.periodicalRunJobs = new TimerTask() {
			@Override
			public void run() {
				try {
					runJobs();
				} catch (StockExchangeException | JobWorkerException e) {
					e.printStackTrace(); // wait until next run
				}
			}
		};

		// add executor for separate thread
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		// run jobs all 5 minutes
		executor.scheduleAtFixedRate(periodicalRunJobs, 0, 300000, TimeUnit.MILLISECONDS);
	}

	private boolean doesShareExist(String isinNo) {
		for (Share obj : this.custodyAccount.getShares()) {
			if (obj.getIsinNo().equals(isinNo)) {
				return true;
			}
		}
		return false;
	}

	private void handleJobTypeBuy(Job job) throws StockExchangeException, JobWorkerException {
		try {
			double currentLimit = job.getLimit();

			if (currentLimit > 0) {
				double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());

				// job has limit
				if (currentMarketPrice <= currentLimit) {
					// limit is reached
					this.buyShare(job);
				}
			} else {
				// has no limit
				this.buyShare(job);
			}
		} catch (StockExchangeException e) {
			throw e;
		} catch (JobWorkerException e) {
			throw e;
		}

	}

	private void buyShare(Job job) throws JobWorkerException, StockExchangeException {
		try {
			double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
			double currentBalance = this.custodyAccount.getAccount().getAccountBalance();

			if (currentMarketPrice <= currentBalance) {
				// buy Share from StockExchange
				Share share = stockExchange.buyShare(job.getIsinNo(), this.custodyAccount.getAccount());
				// add Share to CustodyAccount
				this.custodyAccount.addShare(share);
				System.out.println("Log: Aktie " + job.getIsinNo() + " gekauft.");
				// remove Job from JobWorker
				this.removeJob(job.getId());
			} else {
				throw new JobWorkerException("Kontodeckung nicht ausreichend um die Aktie " + job.getIsinNo() + " von Job " + job.getId() + " zu kaufen.");
			}
		} catch (StockExchangeException e) {
			throw e;
		}
	}

	private void handleJobTypeSell(Job job) throws StockExchangeException, JobWorkerException {
		double currentLimit = job.getLimit();

		try {
			if (currentLimit > 0) {
				double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
				// job has limit
				if (currentMarketPrice <= currentLimit) {
					// limit is reached
					this.sellShare(job);
				}
			} else {
				// has no limit
				this.sellShare(job);
			}
		} catch (StockExchangeException e) {
			throw e;
		} catch (JobWorkerException e) {
			throw e;
		}
	}

	private void sellShare(Job job) throws JobWorkerException, StockExchangeException {
		if (doesShareExist(job.getIsinNo()) == true) {
			Share share = custodyAccount.getShare(job.getIsinNo());
			try {
				double money = stockExchange.sellShare(share);
				// remove share if it could be sold
				this.custodyAccount.removeShare(share);
				// deposit money to account
				this.custodyAccount.getAccount().deposit(money);
				System.out.println("Log: Aktie " + job.getIsinNo() + " verkauft.");
			} catch (StockExchangeException e) {
				throw e;
			}
			this.removeJob(job.getId());
		} else {
			// no matching share found - remove job and throw exception
			this.removeJob(job.getId());
			throw new JobWorkerException("Keine passende Aktie mit der isinNo '" + job.getIsinNo() + "' für den Job nr " + job.getId() + " gefunden. Der Job wurde entfernt.");
		}
	}

}