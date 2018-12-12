
public class Share {
	// attributes
	private String name;
	private double costPrice;
	private String isinNo;
	
	// construct
	public Share(String name, double costPrice, String isinNo) {
		this.name = name;
		this.costPrice = costPrice;
		this.isinNo = isinNo;
	}
	
	// methods
	public String getName() {
		return this.name;
	}
	
	public double getCostPrice() {
		return this.costPrice;
	}
	
	public String getIsinNo() {
		return this.isinNo;
	}
}
