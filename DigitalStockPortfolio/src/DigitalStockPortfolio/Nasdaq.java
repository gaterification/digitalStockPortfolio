package DigitalStockPortfolio;

import java.io.IOException;
import java.math.BigDecimal;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class Nasdaq implements StockExchange {
	@Override
	public Share buyShare(String isinNo, Account account) throws StockExchangeException {
		try {
			double currentMarketPrice = this.getMarketPrice(isinNo);
			try {
				account.disburse(currentMarketPrice);
				// enough money
				Stock stock = this.getShare(isinNo);
				String name = stock.getName();
				return new Share(name, currentMarketPrice, isinNo);
			} catch (AccountException e) {
				throw new StockExchangeException("Nicht genug Geld vorhanden um die Aktie zu kaufen.");
			}
		} catch (StockExchangeException e) {
			throw e;
		}
	}

	@Override
	public double sellShare(Share share) throws StockExchangeException {
		try {
			Stock s = this.getShare(share.getIsinNo());
			BigDecimal b = s.getQuote().getPrice();
			return b.doubleValue();
		} catch (StockExchangeException e) {
			throw e;
		}
	}

	@Override
	public double getMarketPrice(String isinNo) throws StockExchangeException {
		try {
			Stock s = this.getShare(isinNo);
			BigDecimal b = s.getQuote().getPrice();
			return b.doubleValue();
		} catch (StockExchangeException e) {
			throw e;
		}
	}

	private Stock getShare(String isinNo) throws StockExchangeException {
		try {
			Stock s = YahooFinance.get(isinNo);
			return s;
		} catch (IOException e) {
			throw new StockExchangeException("StockExchange kann im Moment nicht erreicht werden oder die Aktie " + isinNo + " kann nicht gefunden werden.");
		}
	}
}
