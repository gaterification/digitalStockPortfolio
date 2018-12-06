import java.io.IOException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class Nasdaq {
public void getMarketPrice(String test) throws IOException {
	Stock s = YahooFinance.get(test);
	System.out.print(s);
}
}
