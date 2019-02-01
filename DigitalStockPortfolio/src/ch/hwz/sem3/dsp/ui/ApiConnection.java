package ch.hwz.sem3.dsp.ui;

import ch.hwz.sem3.dsp.api.ApiDigitalStockPortfolio;
import ch.hwz.sem3.dsp.api.ApiDigitalStockPortfolioException;

public class ApiConnection {
	private ApiDigitalStockPortfolio api;

	public ApiConnection() {
		this.api = new ApiDigitalStockPortfolio();
	}

	// getters/setters
	public ApiDigitalStockPortfolio getApi() {
		return this.api;
	}

	// Methoden f�r die Ausgabe im FrontEnd
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
		if (amount == 0.0) {
			System.out.println("---------------------------------------------------");
			System.out.println("Bitte geben sie einen Betrag ein.");
			System.out.println("---------------------------------------------------");
		} else {
			try {
				this.api.disburse(amount);
				System.out.println("---------------------------------------------------");
				System.out.println(amount + " ausbezahlt");
				System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
				System.out.println("---------------------------------------------------");
			} catch (ApiDigitalStockPortfolioException e) {
				System.out.println("---------------------------------------------------");
				System.out.println("Fehler: " + e.getMessage());
				System.out.println("Account Saldo betraegt: " + api.getAccountBalance());
				System.out.println("---------------------------------------------------");
			}
		}
	}

	public void getMarketPrice(String isinNo) {
		try {
			double marketPrice = this.api.getMarketPrice(isinNo);
			System.out.println("---------------------------------------------------");
			System.out.println("Marktpreis der " + isinNo + " betraegt: " + marketPrice);
			System.out.println("---------------------------------------------------");
		}
		catch(ApiDigitalStockPortfolioException e) {
			System.out.println("---------------------------------------------------");
			System.out.println("Fehler: " + e.getMessage());
			System.out.println("---------------------------------------------------");
		} 
	}

	public void buyShare(String isinNo) {
		this.api.buyShare(isinNo);
		System.out.println("---------------------------------------------------");
		System.out.println("Job erstellt um Aktie " + isinNo + " zu kaufen.");
		System.out.println(this.api.runJobs());
		System.out.println("---------------------------------------------------");
	}

	public void sellShare(String isinNo) {
		try {
			this.api.sellShare(isinNo);
			System.out.println("---------------------------------------------------");
			System.out.println("Job erstellt um Aktie " + isinNo + " zu verkaufen.");
			System.out.println(this.api.runJobs());
			System.out.println("---------------------------------------------------");
		} catch (ApiDigitalStockPortfolioException e) {
			System.out.println("---------------------------------------------------");
			System.out.println("Fehler: " + e.getMessage());
			System.out.println("---------------------------------------------------");
		}
	}

	public void defineLimitBuy(String isinNo, double limit) {
		this.api.defineLimitBuy(isinNo, limit);
		System.out.println("---------------------------------------------------");
		System.out.println("Aktie kaufen mit Limitpreis von: " + limit);
		System.out.println(this.api.runJobs());
		System.out.println("---------------------------------------------------");
	}

	public void defineLimitSell(String isinNo, double limit) {
		this.api.defineLimitSell(isinNo, limit);
		System.out.println("---------------------------------------------------");
		System.out.println("Aktie verkaufen mit Limitpreis von: " + limit);
		System.out.println(this.api.runJobs());
		System.out.println("---------------------------------------------------");
	}

	public void calculateWinOrLoss() {
		try {
			double currentWinOrLoss = this.api.calculateWinOrLoss();
			System.out.println("---------------------------------------------------");
			System.out.println("Aktueller Verlust/Gewinn: " + currentWinOrLoss);
			System.out.println("---------------------------------------------------");
		} catch (ApiDigitalStockPortfolioException e) {
			System.out.println("---------------------------------------------------");
			System.out.println("Fehler: " + e.getMessage());
			System.out.println("---------------------------------------------------");
		}
	}

	public void printJobs() {
		System.out.println("---------------------------------------------------");
		System.out.println("pendente Jobs:\n" + api.printJobs());
		System.out.println("---------------------------------------------------");
	}

	public void removeJob(int jobId) {
		try {
			this.api.removeJob(jobId);
			System.out.println("---------------------------------------------------");
			System.out.println("pendenter Jobs geloescht: " + jobId);
			System.out.println("---------------------------------------------------");
		} catch (ApiDigitalStockPortfolioException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

	public void printShares() {
		System.out.println("---------------------------------------------------");
		System.out.println("Shares in meinem Depot:\n" + api.printShares());
		System.out.println("---------------------------------------------------");
	}
	
	public void runJobs() {
		System.out.println("---------------------------------------------------");
		System.out.println(this.api.runJobs());
		System.out.println("---------------------------------------------------");
	}
}
