package ApiDigitalStockPortfolio;
public class TestApiDigitalStockPortfolioMichel {
	private ApiDigitalStockPortfolio api;
	
	public static void main(String[] args) {
		//String isinNo;
		
		// TODO: @all: moeglichkeiten dokumentieren!!
		TestApiDigitalStockPortfolioMichel apiTest = new TestApiDigitalStockPortfolioMichel();
		ApiDigitalStockPortfolio api = apiTest.getApi();
	
		
		
		
		
		//Get AccountNumber
//		System.out.println("Account-Nummer: " + api.getAccountNumber());
		
		//Get CustodyAccountNumber
//		System.out.println("CustodyAccount-Nummer: " + api.getCustodyAccountNumber());
		
		//Get AccountBalance
//		System.out.println("Account-Saldo: " + api.getAccountBalance());
		
		//Deposit to Account
		api.deposit(10000.0);
		
		//Disburse from Account
//		api.disburse(500.0);
		
		//Get win or loss
//		System.out.println("Aktueller winOrLos: Sollte ein Fehler ausgeben, da noch keine Aktien vorhanden.");
		
		//Buy share
//		api.buyShare("TSLA");
//		api.runJobs(); //Start JobWorker to buy
//		api.buyShare("TSLA");
//		api.buyShare("AAPL");
		
		//Get win or loss
//		System.out.println("Aktueller winOrLos: Sollte ein Fehler ausgeben, da noch keine Aktien vorhanden.");
		
		//Sell share
//		api.sellShare("APPL");
//		api.runJobs(); //Start JobWorker to sell
		
		//Get shares from your deposit (CustodyAccount)
//		System.out.println("Aktuelle Aktien: " + api.printShares());
		
		//Get current buy or sell jobs
//		System.out.println("Aktuelle Jobs: " + api.printJobs());
		
		
		
		//Get MarketPrice (Gibt noch Fehler aus)
//		System.out.println("Aktueller Preis von TSLA: " + api.getMarketPrice("TSLA"));
		
//		System.out.println("Account-Saldo: " + api.getAccountBalance());
		apiTest.buyShare("TSLA");
		apiTest.buyShare("TSLA");
		apiTest.buyShare("TSLA");
		apiTest.buyShare("TSLA");
		apiTest.buyShare("TSLA");
		
		apiTest.definedLimiteBuy("TSLA", 347);
		
		
		
		apiTest.sellShare("TSLA");
		apiTest.sellShare("TSLA");
		apiTest.sellShare("TSLA");
		apiTest.sellShare("TSLA");
		
		apiTest.definedLimiteSell("TSLA", 347);
		
		apiTest.sleep(20);
		apiTest.calculateWinOrLoss();
		
		apiTest.sleep(300);
		apiTest.calculateWinOrLoss();
	
		
	}
	
	
	public TestApiDigitalStockPortfolioMichel() {
		this.api = new ApiDigitalStockPortfolio();
	}
	
	// getters/setters
	public ApiDigitalStockPortfolio getApi() {
		return this.api;
	}
	
	private void buyShare(String isinNo) {
		this.api.buyShare(isinNo);
		System.out.println("-----------------------------------------");
		System.out.println("zum Marktpreis Preis von: " + api.getMarketPrice(isinNo));
		System.out.println("Neuer Account Saldo beträgt: " + api.getAccountBalance());
		System.out.println("-----------------------------------------");
	}
	
	private void sellShare(String isinNo) {
		this.api.sellShare(isinNo);
		System.out.println("-----------------------------------------");
		System.out.println("zum Marktpreis Preis von: " + api.getMarketPrice(isinNo));
		System.out.println("Neuer Account Saldo beträgt: " + api.getAccountBalance());
		System.out.println("-----------------------------------------");
	}
	
	private void definedLimiteBuy(String isinNo, double limit) {
		this.api.definedLimitBuy(isinNo, limit);
		System.out.println("-----------------------------------------");
		System.out.println("Aktueller Marktpreis beträgt: " + api.getMarketPrice(isinNo));
		System.out.println("-----------------------------------------");
	}
	
	private void definedLimiteSell(String isinNo, double limit) {
		this.api.definedLimitSell(isinNo, limit);
		System.out.println("-----------------------------------------");
		System.out.println("Aktueller Marktpreis beträgt: " + api.getMarketPrice(isinNo));
		System.out.println("-----------------------------------------");
	}
	
	private void calculateWinOrLoss() {
	System.out.println("Aktueller Verlust/Gewinn: " + api.calculateWinOrLoss());
	}
	
	private void sleep(int time) {
		System.out.println(time + " Sekunden warten...");
		try {
			java.util.concurrent.TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
