package DigitalStockPortfolio;

public class Share {
	// attributes
	private String name;
	private double costPrice;
	private String isinNo;
	
	// construct
	protected Share(String name, double costPrice, String isinNo) {
		this.name = name;
		this.costPrice = costPrice;
		this.isinNo = isinNo;
	}
	
	// methods
	protected String getName() {
		return this.name;
	}
	
	protected double getCostPrice() {
		return this.costPrice;
	}
	
	protected String getIsinNo() {
		return this.isinNo;
	}
}
