import java.util.ArrayList;

public class CustodyAccount {
	// class attributes
	private static CustodyAccount custodyAccount;	// singleton
	private static int trxNumber = 0;
	
	// attributes
	private int custodyAccountNumber;
	private ArrayList<Share> shares;
	private Account account;
	private JobWorker jobWorker;
	private StockExchange stockExchange;
	
	// construct
	private CustodyAccount(Account account, StockExchange stockExchange) {
		this.custodyAccountNumber = trxNumber;
		trxNumber = trxNumber + 1;
		this.shares = new ArrayList<Share>();
		this.account = account;
		this.stockExchange = stockExchange;
		this.jobWorker = JobWorker.getJobWorker(this.stockExchange, this);
	}
	
	// methods
	public static CustodyAccount getCustodyAccount(Account account, StockExchange stockExchange) {
		if (custodyAccount == null) {
			custodyAccount = new CustodyAccount(account, stockExchange);
		}
		return custodyAccount;
	}
		
	public double calculateWinOrLoss() {
		// TODO: implement
		return 0.0;
	}
	
	public void buyShare(String isinNo) {
		// TODO: implement
	}
	
	public void sellShare(String isinNo) {
		// TODO: implement
	}
	
	public void defineLimitSell(String isinNo, double limit) {
		// TODO: implement
	}
	
	public void defineLimitBuy(String isinNo, double limit) {
		// TODO: implement
	}
	
	public double getMarketPrice(String isinNo) {
		// TODO: implement
		return 0.0;
	}
	
	public void addShare(Share share) {
		this.shares.add(share);
	}
	
	// getters
	public int getCustodyAccountNumber() {
		return this.custodyAccountNumber;
	}
	
	public ArrayList<Share> getShares() {
		return this.shares;
	}
	
	public Account getAccount() {
		return this.account;
	}
}
