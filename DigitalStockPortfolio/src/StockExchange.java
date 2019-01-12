import java.io.IOException;

public abstract class StockExchange {	
	// methods
	public abstract Share buyShare(String isinNo, Account account) throws StockExchangeException;
	public abstract double sellShare(Share share) throws StockExchangeException;
	public abstract double getMarketPrice(String isinNo) throws StockExchangeException;
}
