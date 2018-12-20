
public abstract class StockExchange {
	// attributes
	
	// construct : establish connection
	
	// methods
	public abstract Share buyShare(String isinNo, double price);
	public abstract double sellShare(Share share);
	public abstract double getMarketPrice(String isinNo);
}
