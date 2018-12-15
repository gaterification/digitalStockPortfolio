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
	private CustodyAccount(Account account) {
		this.custodyAccountNumber = trxNumber;
		trxNumber = trxNumber + 1;
		this.shares = new ArrayList<Share>();
		this.account = account;
		this.stockExchange = new Nasdaq();
		this.jobWorker = JobWorker.getJobWorker(this.stockExchange, this); // TODO: singleton
	}
	
	// methods
	public static CustodyAccount getCustodyAccount(Account account) {
		if (custodyAccount == null) {
			custodyAccount = new CustodyAccount(account);
		}
		return custodyAccount;
	}
		
	public double calculateWinOrLoss() {
		// Arraylist mit vorhandenen Aktien aufrufen und Kaufwert zwischenspeichern, aktienkurs von vorhandenen aktien abrufen, 
		// aktienkus aktuell und kaufprais verlgeichen, Neuer Wert - alter Wert = WinOrLoss
		return 0.0;
	}
	
	public void buyShare(String isinNo) {
		Job job = new Job(isinNo, JobType.BUY);
		this.jobWorker.addJob(job);
	}
	
	public void sellShare(String isinNo) {
		Job job = new Job(isinNo, JobType.SELL);
		this.jobWorker.addJob(job);
	}
	
	public void defineLimitSell(String isinNo, double limit) {
		Job job = new Job(isinNo, JobType.SELL, limit);
		this.jobWorker.addJob(job);

	}
	
	public void defineLimitBuy(String isinNo, double limit) {
		Job job = new Job(isinNo, JobType.BUY, limit);
		this.jobWorker.addJob(job);
	}
	
	public double getMarketPrice(String isinNo) {
		// TODO: implement
		return 0.0;
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
