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
		// TODO: implement
	}
	
	public void addJob(Job job) {
		// TODO: implement
	}
	
	public void runJobs(CustodyAccount custodyAccount) {
		// TODO: implement
	}
	
	public ArrayList<Job> getJobs() {
		return this.jobs;
	}
}
