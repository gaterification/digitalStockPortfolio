package FrontEndDigitalStockPortfolio;

import ApiDigitalStockPortfolio.ApiDigitalStockPortfolio;

public class TestApiDigitalStockPortfolioMichel {
	private ApiDigitalStockPortfolio api;
	
//	private static FrontEndJFrame myFrame;
	
	public static void main(String[] args) {
		
		
		// TODO: @all: moeglichkeiten dokumentieren!!
		TestApiDigitalStockPortfolioMichel apiTest = new TestApiDigitalStockPortfolioMichel();
		ApiDigitalStockPortfolio api = apiTest.getApi();
	
		FrontEnd test = new FrontEnd(apiTest);
		
		
		
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

		api.printShares();
		api.getShares();
		
/*		apiTest.buyShare("TSLA");
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
	
*/		
	}
	
	//methods
	double amount;
	
	public TestApiDigitalStockPortfolioMichel() {
		this.api = new ApiDigitalStockPortfolio();
	}
	
	// getters/setters
	public ApiDigitalStockPortfolio getApi() {
		return this.api;
	}
	
	public void getAccountBalance() {
		System.out.println("---------------------------------------------------");
		System.out.println("Account Saldo betraegt: " + api.getAccountBalance());
		System.out.println("---------------------------------------------------");
	}
	
	public void deposit(double amount) {
		this.api.deposit(amount);
		System.out.println("---------------------------------------------------");
		System.out.println(amount + " einbezahlt");
		System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
		System.out.println("---------------------------------------------------");
	}
	
	public void disburse(double amount) {
		this.api.disburse(amount);
		System.out.println("---------------------------------------------------");
		System.out.println(amount + " ausbezahlt");
		System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
		System.out.println("---------------------------------------------------");
	}
	
	public void getMarketPrice(String isinNo) {
		this.api.getMarketPrice(isinNo);
		System.out.println("---------------------------------------------------");
		System.out.println("Marktpreis der " + isinNo + " betraegt: " + api.getMarketPrice(isinNo)); //Ausgabe isinNo noch testen
		System.out.println("---------------------------------------------------");
	}
	
	public void buyShare(String isinNo) {
		this.api.buyShare(isinNo);
		System.out.println("---------------------------------------------------");
		System.out.println("Aktie kaufen zum Marktpreis Preis von: " + api.getMarketPrice(isinNo));
		System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
		System.out.println("---------------------------------------------------");
	}
	
	public void sellShare(String isinNo) {
		this.api.sellShare(isinNo);
		System.out.println("---------------------------------------------------");
		System.out.println("Aktie verkaufen zum Marktpreis Preis von: " + api.getMarketPrice(isinNo));
		System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
		System.out.println("---------------------------------------------------");
	}
	
	public void definedLimitBuy(String isinNo, double limit) {
		this.api.definedLimitBuy(isinNo, limit);
		System.out.println("---------------------------------------------------");
		System.out.println("Aktie kaufen mit Limitpreis von: " + limit);
		System.out.println("---------------------------------------------------");
	}
	
	public void definedLimiteSell(String isinNo, double limit) {
		this.api.definedLimitSell(isinNo, limit);
		System.out.println("---------------------------------------------------");
		System.out.println("Aktie verkaufen mit Limitpreis von: " + limit);
		System.out.println("---------------------------------------------------");
	}
	
	public void calculateWinOrLoss() {
		System.out.println("---------------------------------------------------");
		System.out.println("Aktueller Verlust/Gewinn: " + api.calculateWinOrLoss());
		System.out.println("---------------------------------------------------");
	}
	
	public void printJobs() {
		System.out.println("---------------------------------------------------");
		System.out.println("pendente Jobs: " + api.printJobs());
		System.out.println("---------------------------------------------------");
	}
	
	public void removeJobs(int jobId) {
		this.api.removeJobs(jobId);
		System.out.println("---------------------------------------------------");
		System.out.println("pendenter Jobs geloescht: " + jobId);
		System.out.println("---------------------------------------------------");
	}
	
	public void getShares() {
		System.out.println("---------------------------------------------------");
		System.out.println("Shares: " + api.getShares());
		System.out.println("---------------------------------------------------");
	}
	
	public void printShares() {
		System.out.println("---------------------------------------------------");
		System.out.println("Shares in meinem Depot:\n" + api.printShares());
		System.out.println("---------------------------------------------------");
	}
	
	public void sleep(int time) {
		System.out.println(time + " Sekunden warten...");
		try {
			java.util.concurrent.TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
