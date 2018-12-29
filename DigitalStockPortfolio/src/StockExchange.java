import java.io.IOException;

public abstract class StockExchange {
	// attributes
	
	// construct : establish connection
	
	// methods
	public abstract Share buyShare(String isinNo, double price) throws StockExchangeException;
	public abstract double sellShare(Share share) throws StockExchangeException;
	public abstract double getMarketPrice(String isinNo) throws StockExchangeException;
}
