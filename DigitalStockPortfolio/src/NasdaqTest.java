import java.io.IOException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


// TODO: remove legacy-Code --> this class is not used!
public class NasdaqTest {
	public static void main(String[] args) {
		Nasdaq n = new Nasdaq();
		Share s = null;
		try {
			double money = n.getMarketPrice("TSLA");
			System.out.println("Preis TSLA: " + money);
			try {
				s = n.buyShare("TSLA", money);
				System.out.println("Neuer Share: Name: " + s.getName() + ", isinNo: " + s.getIsinNo() + ", Price: " + s.getCostPrice());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			
		}
		
		try {
			double money = n.sellShare(s);
			System.out.println("Geld zurück: " + money);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			
		}
		
	}

//	Stock stock = YahooFinance.get("TSLA");
//	Stock stock = new Stock("TSLA");
	
}
