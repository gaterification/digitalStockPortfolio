import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

// import java.io.IOException;

public class Nasdaq extends StockExchange {

	public void getMarketPriceTest(String test) throws IOException {
		Stock s = YahooFinance.get(test);
		System.out.print(s);
	}

	@Override
	public Share buyShare(String isinNo, double money) throws StockExchangeException {
		try {
			double currentMarketPrice = this.getMarketPrice(isinNo);
			if (money == currentMarketPrice) {
				// enough money given
				Stock stock = this.getShare(isinNo);
				String name = stock.getName();
				return new Share(name, money, isinNo);
			} else if (money < currentMarketPrice) {
				// not enough money given to buy share
				throw new StockExchangeException("Nicht genug Geld übergeben um die Aktie zu kaufen.");
			} else {
				// not enough money given to buy share
				throw new StockExchangeException("Nicht genug Geld übergeben um die Aktie zu kaufen.");
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
			// TODO Auto-generated catch block
			throw e;
		}
	}

	private Stock getShare(String isinNo) throws StockExchangeException {
		try {
			Stock s = YahooFinance.get(isinNo);
			return s;
		} catch (IOException e) {
			// TODO: Unterschiedliche Exception werfen je nachdem ob YahooFinance nicht
			// erreichbar oder isinNo nicht gefunden wird...
			throw new StockExchangeException("StockExchange kann im Moment nicht erreicht werden.");
		}

	}
}
