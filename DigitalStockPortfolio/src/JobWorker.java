import java.util.ArrayList;

public class JobWorker {
	// class attributes
	private static JobWorker jobWorker;
	
	// attributes
	private ArrayList<Job> jobs;
	private StockExchange stockExchange;
	
	// construct
	private JobWorker(StockExchange stockExchange) {
		this.jobs = new ArrayList<Job>();
		this.stockExchange = stockExchange;
	}
	
	// methods
	public static JobWorker getJobWorker(StockExchange stockExchange) {
		if (jobWorker == null) {
			jobWorker = new JobWorker(stockExchange);
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
			if (job.getJobType() == JobType.BUY) {
				
			} else if (job.getJobType() == JobType.SELL) {
				
			}
		}
	}
	
	public ArrayList<Job> getJobs() {
		return this.jobs;
	}
}
