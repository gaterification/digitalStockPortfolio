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
	
	public void runJobs(CustodyAccount custodyAccount) {
		System.out.println("Run at: " + new Date());
		for (Job job : this.jobs) {
			if (job.getJobType() == JobType.BUY) {
				
			} else if (job.getJobType() == JobType.SELL) {
				
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
				runJobs(custodyAccount);
			}
		};

		// add executor for separate thread
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		// run jobs all 5 minutes
		// TODO: change from 5 seconds to 5 minutes...
		executor.scheduleAtFixedRate(periodicalRunJobs, 0, 5000, TimeUnit.MILLISECONDS);
	}
}
