// import java.io.IOException;

public class Nasdaq extends StockExchange {
	/*public void getMarketPriceTest(String test) throws IOException {
		Stock s = YahooFinance.get(test);
		System.out.print(s);
	}*/

	@Override
	public Share buyShare(String isinNo, double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double sellShare(Share share) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double getMarketPrice(String isinNo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
