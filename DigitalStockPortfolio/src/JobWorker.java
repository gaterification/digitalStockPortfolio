import java.util.ArrayList;

import javax.security.auth.login.AccountException;

public class JobWorker {
	// class attributes
	private static JobWorker jobWorker;

	// attributes
	private ArrayList<Job> jobs;
	private StockExchange stockExchange;
	private CustodyAccount custodyAccount;

	// construct
	private JobWorker(StockExchange stockExchange, CustodyAccount custodyAccount) {
		this.jobs = new ArrayList<Job>();
		this.stockExchange = stockExchange;
		this.custodyAccount = custodyAccount;
	}

	// methods
	public static JobWorker getJobWorker(StockExchange stockExchange, CustodyAccount custodyAccount) {
		if (jobWorker == null) {
			jobWorker = new JobWorker(stockExchange, custodyAccount);
		}
		return jobWorker;
	}

	public void removeJob(int id) {
		this.jobs.removeIf(e -> e.getId() == id);
	}

	public void addJob(Job job) {
		this.jobs.add(job);
	}

	public void runJobs(CustodyAccount custodyAccount) {
		for (Job job : this.jobs) {
			double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
			double currentBalance = this.custodyAccount.getAccount().getAccountBalance();
			double currentLimit = job.getLimit();
			
			if (job.getJobType() == JobType.BUY) {
				if (currentLimit > 0) {
					if (currentMarketPrice >= currentLimit) { // limit buy
						if (currentMarketPrice == currentBalance) {
							Share share = stockExchange.buyShare(job.getIsinNo(),
									this.custodyAccount.getAccount().disburse(currentMarketPrice));// buy share
							this.custodyAccount.addShare(share);
							this.removeJob(job.getId());
						} else {
							// do not buy share
						}
					}
				} else if (currentBalance == currentMarketPrice) { // buy at market
					Share share = stockExchange.buyShare(job.getIsinNo(),
							this.custodyAccount.getAccount().disburse(currentMarketPrice));// buy share
					this.custodyAccount.addShare(share);
					this.removeJob(job.getId());// Job done and deleted
				} else {
					// do not Buy share
				}
			} else if (job.getJobType() == JobType.SELL) {
				if (doesShareExist(job.getIsinNo())) {// share exists
					if (currentLimit > 0) {
						if (currentMarketPrice >= currentLimit) { // limit sell
							this.custodyAccount.getShares();
							custodyAccount.sellShare(job.getIsinNo());
							this.custodyAccount.removeShare(share);
							this.removeJob(job.getId());
						}
					}else {
						custodyAccount.sellShare(job.getIsinNo());
								this.custodyAccount.getAccount().deposit(currentMarketPrice);
						this.removeJob(job.getId());
						this.custodyAccount.removeShare(share);
						}
				} else {
					// share does not exist
				}

			}
		}
	}

	

	public ArrayList<Job> getJobs() {
		return this.jobs;
	}

	private boolean doesShareExist(String isinNo) {
		for (Share obj : this.custodyAccount.getShares()) {
			if (obj.getIsinNo().equals(isinNo)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}




