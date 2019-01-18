import java.util.ArrayList;

public class ApiDigitalStockPortfolio {
	private Account account;
	private CustodyAccount custodyAccount;
	
	public ApiDigitalStockPortfolio() {
		// default StockExchange = Nasdaq
		this.initializeDigitaltStockPortfolio(new Nasdaq());
	}
	
	public ApiDigitalStockPortfolio(StockExchange stockExchange) {
		this.initializeDigitaltStockPortfolio(stockExchange);
	}
	
	// setup
	private void initializeDigitaltStockPortfolio(StockExchange stockExchange) {
		this.account = Account.getAccount();
		this.account.addCustodyAccount(stockExchange);
		this.custodyAccount = this.account.getCustodyAccount();
	}
	
	// methods
	public double disburse(double amount) {
		try {
			return this.account.disburse(amount);
		} catch (AccountException e) {
			System.err.println("Nicht genug Geld vorhanden um " + amount + " abzuheben.");
		}
		return 0.0;
	}
	
	public void deposit(double amount) {
		this.account.deposit(amount);
	}
	
	public double getAccountBalance() {
		return this.account.getAccountBalance();
	}
	
	public int getAccountNumber() {
		return this.account.getAccountNumber();
	}
	
	public int getCustodyAccountNumber() {
		return this.custodyAccount.getCustodyAccountNumber();
	}
	
	public double getMarketPrice(String isinNo) {
		try {
			return this.custodyAccount.getMarketPrice(isinNo);
		} catch (StockExchangeException e) {
			System.err.println(e.getMessage() + " Die Angabe von 0.0 allenfalls ist nicht korrekt.");
		}
		return 0.0;
	}
	
	public double calculateWinOrLoss() {
		try {
			return this.custodyAccount.calculateWinOrLoss();
		} catch (CustodyAccountException | StockExchangeException e) {
			System.err.println(e.getMessage() + " Die Angabe von 0.0 allenfalls ist nicht korrekt.");
		}
		return 0.0;
	}
	
	public ArrayList<Share> getShares() {
		return this.custodyAccount.getShares();
	}
	
	public ArrayList<Job> getJobs() {
		return this.custodyAccount.getJobs();
	}
	
	public void buyShare(String isinNo) {
		this.custodyAccount.buyShare(isinNo);
		this.runJobs();
	}
	
	public void definedLimitBuy(String isinNo, double limit) {
		this.custodyAccount.defineLimitBuy(isinNo, limit);
		this.runJobs();
	}
	
	public void sellShare(String isinNo) {
		this.custodyAccount.sellShare(isinNo);
		this.runJobs();
	}
	
	public void definedLimitSell(String isinNo, double limit) {
		this.custodyAccount.defineLimitSell(isinNo, limit);
		this.runJobs();
	}
	
	public void runJobs() {
		try {
			this.custodyAccount.runJobs();
		} catch (StockExchangeException | JobWorkerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	//Detail for sell or buy (michel)
	public void getDetails(String isinNo) {
		getMarketPrice(isinNo);
		getAccountBalance();
	}
}
