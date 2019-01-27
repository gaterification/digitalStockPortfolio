package ApiDigitalStockPortfolio;

import java.util.ArrayList;
import DigitalStockPortfolio.Account;
import DigitalStockPortfolio.AccountException;
import DigitalStockPortfolio.CustodyAccount;
import DigitalStockPortfolio.CustodyAccountException;
import DigitalStockPortfolio.Job;
import DigitalStockPortfolio.Nasdaq;
import DigitalStockPortfolio.Share;
import DigitalStockPortfolio.StockExchange;
import DigitalStockPortfolio.StockExchangeException;

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
			System.err.println(e.getMessage());
			return 0.0;
		}
	}
	
	public boolean deposit(double amount) {
		this.account.deposit(amount);
		return true;
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
			System.err.println(e.getMessage());
			return 0.0;
		}
	}
	
	public double calculateWinOrLoss() {
		try {
			return this.custodyAccount.calculateWinOrLoss();
		} catch (CustodyAccountException | StockExchangeException e) {
			System.err.println(e.getMessage());
			return 0.0;
		}
	}
	
	public ArrayList<Share> getShares() {
		return this.custodyAccount.getShares();
	}
	
	public String printShares() {
		return this.custodyAccount.printShares();
	}

	public ArrayList<Job> getJobs() {
		return this.custodyAccount.getJobs();
	}
	
	public String printJobs() {
		return this.custodyAccount.printJobs();
	}
	
	public boolean buyShare(String isinNo) {
		this.custodyAccount.buyShare(isinNo);
		return true;
	}
	
	public boolean defineLimitBuy(String isinNo, double limit) {
		this.custodyAccount.defineLimitBuy(isinNo, limit);
		return true;
	}
	
	public String sellShare(String isinNo) {
		if (this.custodyAccount.doesShareExist(isinNo)) {
			try {
				this.custodyAccount.sellShare(isinNo);
				return "Aktie " + isinNo + " verkauft.";
			} catch (CustodyAccountException e) {
				System.err.println(e.getMessage());
				return "Fehler: " + e.getMessage();
			}
		} else {
			// return custom error
			return "Fehler: Es existiert keine Aktie mit der isinNo '" + isinNo + "'.";
		}
	}
	
	public boolean defineLimitSell(String isinNo, double limit) {
		this.custodyAccount.defineLimitSell(isinNo, limit);
		return true;
	}
	
	public String runJobs() {
		return this.custodyAccount.runJobs();
	}
	
	public String removeJob(int jobId) {
		try {
			this.custodyAccount.removeJob(jobId);
			return "Job mit ID " + jobId + " wurde entfernt.";
		} catch (CustodyAccountException e) {
			System.err.println(e.getMessage());
			return "Fehler: Job mit ID " + jobId + " konnte nicht entfernt werden.";
		}
	}
}
