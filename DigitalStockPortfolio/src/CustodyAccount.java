import java.util.ArrayList;

public class CustodyAccount {
	// class attributes
	private static CustodyAccount custodyAccount; // singleton
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
		this.jobWorker = JobWorker.getJobWorker(this.stockExchange); // TODO: singleton
	}

	// methods
	public static CustodyAccount getCustodyAccount(Account account) {
		if (custodyAccount == null) {
			custodyAccount = new CustodyAccount(account);
		}
		return custodyAccount;
	}

	public double calculateWinOrLoss() {
		// variabel für den End winOrLoss, wird beim durchschlaufen der arraylist
		// angepasst

		double winOrLoss = 0.0;

		if (this.shares.size() > 0) {

			// schlaufe um die ganze arraylist durchzugehen
			for (Share currentShare : this.shares) {
				// Variabel um die IsinNo des aktuellen Shares auszulesen und zwischenspeichern.
				// Wird gebraucht um den aktuellen Wert danach abzufragen.
				String shareIsinNo = currentShare.getIsinNo();
				// Variabel um den alten Preis des aktuellen Shares auszulesn und
				// zwischenspeichern
				double oldPrice = currentShare.getCostPrice();
				// Variabel um den aktuellen Preis auszulesn und zwischenspeichern
				double actuallPrice = this.stockExchange.getMarketPrice(shareIsinNo);
				// Variabel um den aktuellen und den alten Preis zu vergleichen und ausrechnen
				double calculate = actuallPrice - oldPrice;

				winOrLoss = winOrLoss + calculate;
			}

		} else {
			System.out.println("Keine Aktien vorhanden");
		}
		return winOrLoss;

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
		// TODO: noetig? --> Kann mit this.stockExchange.getMarketPrice() gemacht werden
		// --> noetig falls Abfrage von MarketPrice aus main gebraucht wird, dann
		// braucht die main kein Zugriff auf den StockExchange
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
