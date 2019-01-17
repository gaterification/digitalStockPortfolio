package DigitalStockPortfolio;

public interface StockExchange {	
	// methods
	public Share buyShare(String isinNo, Account account) throws StockExchangeException;
	public double sellShare(Share share) throws StockExchangeException;
	public double getMarketPrice(String isinNo) throws StockExchangeException;
}
