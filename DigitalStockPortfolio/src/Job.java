
public class Job {
	// class variables
	private static int trxNumber = 0;
	
	// attributes
	private String isinNo;
	private int id;
	private double limit;
	private JobType type;
	
	// construct
	public Job (String isinNo, JobType type) {
		this.id = trxNumber;
		trxNumber = trxNumber + 1;
		this.isinNo = isinNo;
		this.limit = 0;
		this.type = type;
	}
	
	public Job(String isinNo, JobType type, double limit) {
		this.id = trxNumber;
		trxNumber = trxNumber + 1;
		this.isinNo = isinNo;
		this.type = type;
		this.limit = limit;
	}
	
	// methods
	public String getIsinNo() {
		return this.isinNo;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getLimit() {
		return this.limit;
	}
	
	public JobType getJobType() {
		return this.type;
	}
}
