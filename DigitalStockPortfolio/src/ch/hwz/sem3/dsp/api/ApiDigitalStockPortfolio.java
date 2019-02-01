package ch.hwz.sem3.dsp.api;

import java.util.ArrayList;

import ch.hwz.sem3.dsp.core.Account;
import ch.hwz.sem3.dsp.core.AccountException;
import ch.hwz.sem3.dsp.core.CustodyAccount;
import ch.hwz.sem3.dsp.core.CustodyAccountException;
import ch.hwz.sem3.dsp.core.Job;
import ch.hwz.sem3.dsp.core.Nasdaq;
import ch.hwz.sem3.dsp.core.Share;
import ch.hwz.sem3.dsp.core.StockExchange;
import ch.hwz.sem3.dsp.core.StockExchangeException;

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
	public double disburse(double amount) throws ApiDigitalStockPortfolioException {
		try {
			return this.account.disburse(amount);
		} catch (AccountException e) {
			System.err.println(e.getMessage());
			throw new ApiDigitalStockPortfolioException(e.getMessage());
		}
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
	
	public double getMarketPrice(String isinNo) throws ApiDigitalStockPortfolioException {
		try {
			return this.custodyAccount.getMarketPrice(isinNo);
		} catch (StockExchangeException e) {
			System.err.println(e.getMessage());
			throw new ApiDigitalStockPortfolioException(e.getMessage());
		}
	}
	
	public double calculateWinOrLoss() throws ApiDigitalStockPortfolioException {
		try {
			return this.custodyAccount.calculateWinOrLoss();
		} catch (CustodyAccountException | StockExchangeException e) {
			System.err.println(e.getMessage());
			throw new ApiDigitalStockPortfolioException(e.getMessage());
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
	
	public void buyShare(String isinNo) {
		this.custodyAccount.buyShare(isinNo);
	}
	
	public void defineLimitBuy(String isinNo, double limit) {
		this.custodyAccount.defineLimitBuy(isinNo, limit);
	}
	
	public void sellShare(String isinNo) throws ApiDigitalStockPortfolioException {
		if (this.custodyAccount.doesShareExist(isinNo)) {
			try {
				this.custodyAccount.sellShare(isinNo);
			} catch (CustodyAccountException e) {
				System.err.println(e.getMessage());
				throw new ApiDigitalStockPortfolioException(e.getMessage());
			}
		} else {
			throw new ApiDigitalStockPortfolioException("Es existiert keine Aktie mit der isinNo '" + isinNo + "'.");
		}
	}
	
	public void defineLimitSell(String isinNo, double limit) {
		this.custodyAccount.defineLimitSell(isinNo, limit);
	}
	
	public String runJobs() {
		return this.custodyAccount.runJobs();
	}
	
	public void removeJob(int jobId) throws ApiDigitalStockPortfolioException {
		try {
			this.custodyAccount.removeJob(jobId);
		} catch (CustodyAccountException e) {
			System.err.println(e.getMessage());
			throw new ApiDigitalStockPortfolioException(e.getMessage());
		}
	}
}
