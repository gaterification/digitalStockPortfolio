package DigitalStockPortfolio;

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
	public CustodyAccount(Account account, StockExchange stockExchange) {
		this.trxNumber = this.trxNumber + 1;
		this.custodyAccountNumber = this.trxNumber;
		this.shares = new ArrayList<Share>();
		this.account = account;
		this.stockExchange = stockExchange;
		this.jobWorker = new JobWorker(this.stockExchange, this);
	}

	public double calculateWinOrLoss() throws CustodyAccountException, StockExchangeException {
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
				try {
					// Variabel um den aktuellen Preis auszulesn und zwischenspeichern
					double actualPrice = this.stockExchange.getMarketPrice(shareIsinNo);
					// Variabel um den aktuellen und den alten Preis zu vergleichen und ausrechnen
					double calculate = actualPrice - oldPrice;

					winOrLoss = winOrLoss + calculate;
				} catch (StockExchangeException e) {
					throw e;
				}
			}
		} else {
			throw new CustodyAccountException("Keine Aktien vorhanden um winOrLoss zu kalkulieren.");
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

	public double getMarketPrice(String isinNo) throws StockExchangeException {
		try {
			return this.stockExchange.getMarketPrice(isinNo);
		} catch (StockExchangeException e) {
			throw e;
		}
	}
	
	protected void addShare(Share share) {
		this.shares.add(share);
	}
	
	public ArrayList<Job> getJobs() {
		return this.jobWorker.getJobs();
	}
	
	public String printJobs() {
		String jobString = "";
		ArrayList<String> jobsAsString = new ArrayList<String>();
		ArrayList<Job> jobs = this.getJobs();
		if (jobs.size() > 0) {
			for (Job job : jobs) {
				String limitAsString = "";
				if (job.getLimit() > 0.0) {
					limitAsString = ", Limit:" + job.getLimit();
				}
				jobsAsString.add("ID: " + Integer.toString(job.getId()) + ", Typ: " + job.getJobType() + ", isinNo: " + job.getIsinNo() + limitAsString);
			}
			jobString = String.join(" | ", jobsAsString);
		} else {
			jobString =  "Keine Jobs vorhanden die ausgegeben werden koennen.";
		}
		return jobString;
	}
	
	public void runJobs() throws StockExchangeException, JobWorkerException {
		try {
			this.jobWorker.runJobs();
		} catch (StockExchangeException | JobWorkerException e) {
			throw e;
		}
	}
	
	public int getCustodyAccountNumber() {
		return this.custodyAccountNumber;
	}

	public ArrayList<Share> getShares() {
		return this.shares;
	}
	
	public String printShares() {
		String shareString = "";
		ArrayList<String> sharesAsString = new ArrayList<String>();
		if (shares.size() > 0) {
			for (Share share : this.getShares()) {
				sharesAsString.add("Name: " + share.getName() + ", IsinNo: " + share.getIsinNo() + ", gekauft fuer: " + share.getCostPrice());
			}
			shareString = String.join( "\n", sharesAsString);
		} else {
			shareString = "Keine Aktien vorhanden die ausgegeben werden koennen.";
		}
		return shareString;
	}
	
	public Share getShare(String isinNo) {
		for (Share share : this.shares) {
			if(share.getIsinNo()==isinNo) {
				return share;
			}
		}
		// TODO: add custom Exception
		return null;
	}
	
	protected void removeShare(Share share) {
		this.shares.removeIf(e -> e == share);
	}
	
	public void removeJob(int jobId) {
		this.jobWorker.removeJob(jobId);
	}

	protected Account getAccount() {
		return this.account;
	}
}
