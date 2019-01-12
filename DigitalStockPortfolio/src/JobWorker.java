import java.util.ArrayList;
// TODO: remove, should not be used, only for testing
import java.util.Date;
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
	public JobWorker(StockExchange stockExchange, CustodyAccount custodyAccount) {
		this.jobs = new ArrayList<Job>();
		this.stockExchange = stockExchange;
		this.custodyAccount = custodyAccount;
		this.initializePeriodicalRunJobs();
	}

	public void removeJob(int id) {
		this.jobs.removeIf(e -> e.getId() == id);
	}

	public void addJob(Job job) {
		this.jobs.add(job);
	}

	public void runJobs() {
		// TODO: zuerst sollen alle shares verkauft werden --> ArrayList sortieren...
		for (Job job : this.jobs) {
			if (job.getJobType() == JobType.BUY) {
				this.handleJobTypeBuy(job);
			} else {
				this.handleJobTypeSell(job); // do not buy share
			}
		}
	}

	public ArrayList<Job> getJobs() {
		return this.jobs;
	}

	private void initializePeriodicalRunJobs() {
		// initialize timer
		this.periodicalRunJobs = new TimerTask() {
			@Override
			public void run() {
				runJobs();
			}
		};

		// add executor for separate thread
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		// run jobs all 5 minutes
		// TODO: change from 5 seconds to 5 minutes...
		executor.scheduleAtFixedRate(periodicalRunJobs, 0, 5000, TimeUnit.MILLISECONDS);
	}

	private boolean doesShareExist(String isinNo) {
		for (Share obj : this.custodyAccount.getShares()) {
			if (obj.getIsinNo().equals(isinNo)) {
				return true;
			}
		}
		return false;
	}

	private void handleJobTypeBuy(Job job) {
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
			System.err.println(e.toString());
			e.printStackTrace();
		} catch (JobWorkerException e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}

	}

	private void buyShare(Job job) throws JobWorkerException {
		try {
			double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
			double currentBalance = this.custodyAccount.getAccount().getAccountBalance();

			if (currentMarketPrice <= currentBalance) {
				// get money from account
				double money = this.custodyAccount.getAccount().disburse(currentMarketPrice);
				// buy Share from StockExchange
				Share share = stockExchange.buyShare(job.getIsinNo(), money);
				// add Share to CustodyAccount
				this.custodyAccount.addShare(share);
				// remove Job from JobWorker
				this.removeJob(job.getId());
			} else {
				throw new JobWorkerException("Kontodeckung nicht ausreichend um die Aktie " + job.getIsinNo()
						+ " von Job " + job.getId() + " zu kaufen.");
			}
		} catch (StockExchangeException e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private void handleJobTypeSell(Job job) {
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
			System.err.println(e.toString());
			e.printStackTrace();
		} catch (JobWorkerException e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private void sellShare(Job job) throws JobWorkerException {
		if (doesShareExist(job.getIsinNo()) == true) {
			Share share = custodyAccount.getShare(job.getIsinNo());
			try {
				double money = stockExchange.sellShare(share);
				// remove share if it could be sold
				this.custodyAccount.removeShare(share);
				// deposit money to account
				custodyAccount.getAccount().deposit(money);
			} catch (StockExchangeException e) {
				System.err.println(e.toString());
				e.printStackTrace();
			}
			this.removeJob(job.getId());
		} else {
			// no matching share found - remove job and throw exception
			this.removeJob(job.getId());
			throw new JobWorkerException("Keine passende Aktie mit der isinNo " + job.getIsinNo() + " für den Job "
					+ job.getId() + " gefunden. Der Job wurde entfernt.");
		}
	}

}