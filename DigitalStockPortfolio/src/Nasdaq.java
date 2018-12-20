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
	 

	Nasdaq() {
		
	}

	@Override
	public Share buyShare(String isinNo, double money) throws IOException {
		// TODO add custom Exception
		if(money == getMarketPrice(isinNo)){
			String name = YahooFinance.get(isinNo).getName();
			Share s = new Share(name, money, isinNo);
			return s;
		}
		

		return null;
	}

	@Override
	public double sellShare(Share share) throws IOException {
		// TODO add custom Exception
		Stock s = YahooFinance.get(share.getIsinNo());
		BigDecimal b = s.getQuote().getPrice();
		return b.doubleValue();
	}

		
	
	@Override
	public double getMarketPrice(String isinNo) throws IOException {
		// TODO add custom Exception
		Stock s = YahooFinance.get(isinNo);
		BigDecimal b = s.getQuote().getPrice();
		return b.doubleValue();
	}
}
