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
	private JobWorker(StockExchange stockExchange, CustodyAccount custodyAccount) {
		this.jobs = new ArrayList<Job>();
		this.stockExchange = stockExchange;
		this.custodyAccount = custodyAccount;
		this.initializePeriodicalRunJobs();
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

	public void runJobs() {
		// TODO: zuerst sollen alle shares verkauft werden
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
		double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
		double currentLimit = job.getLimit();
		
		if (currentLimit > 0) {
			// job has limit
			if (currentMarketPrice <= currentLimit) {
				// limit is reached
				this.buyShare(job);
			}
		} else {
			// has no limit
			this.buyShare(job);
		}
	}
	
	private void buyShare(Job job) {
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
		}
		
	}

	private void handleJobTypeSell(Job job) {
		double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
		double currentLimit = job.getLimit();
		
		if (currentLimit > 0) {
			// job has limit
			if (currentMarketPrice <= currentLimit) {
				// limit is reached
				this.sellShare(job);
			}
		} else {
			// has no limit
			this.sellShare(job);
		}

	}
	private void sellShare(Job job) {
		double currentMarketPrice = stockExchange.getMarketPrice(job.getIsinNo());
		if(doesShareExist(job.getIsinNo()) == true) {
			Share share = custodyAccount.getShare(job.getIsinNo());
			this.custodyAccount.removeShare(share);
			double money = stockExchange.sellShare(share);
			custodyAccount.getAccount().deposit(money);
			this.removeJob(job.getId());
		}
	}
	
}