package ch.hwz.sem3.dsp.core;

public class Job {
	// class variables
	private static int trxNumber = 0;
	
	// attributes
	private String isinNo;
	private int id;
	private double limit;
	private JobType type;
	
	// construct
	protected Job (String isinNo, JobType type) {
		this.id = ++ trxNumber;
		this.isinNo = isinNo;
		this.limit = 0;
		this.type = type;
	}
	
	protected Job(String isinNo, JobType type, double limit) {
		this(isinNo, type);		
		this.limit = limit;
	}
	
	// methods
	protected String getIsinNo() {
		return this.isinNo;
	}
	
	protected int getId() {
		return this.id;
	}
	
	protected double getLimit() {
		return this.limit;
	}
	
	protected JobType getJobType() {
		return this.type;
	}
}
