package FrontEndDigitalStockPortfolio;

import ApiDigitalStockPortfolio.ApiDigitalStockPortfolio;
import DigitalStockPortfolio.CustodyAccountException;

public class ApiConnection {
	private ApiDigitalStockPortfolio api;

	public ApiConnection() {
		this.api = new ApiDigitalStockPortfolio();
	}

	// getters/setters
	public ApiDigitalStockPortfolio getApi() {
		return this.api;
	}

	// Methoden für die Ausgabe im FrontEnd
	public void getAccountBalance() {
		System.out.println("---------------------------------------------------");
		System.out.println("Account Saldo betraegt: " + api.getAccountBalance());
		System.out.println("---------------------------------------------------");
	}

	public void deposit(double amount) {
		if (this.api.deposit(amount)) {
			System.out.println("---------------------------------------------------");
			System.out.println(amount + " einbezahlt");
			System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println(amount + " konnte nicht einbezahlt werden.");
			System.out.println("Bitte versuchen Sie es erneut.");
			System.out.println("---------------------------------------------------");
		}
	}

	public void disburse(double amount) {
		if (amount == 0.0 ) {
			System.out.println("---------------------------------------------------");
			System.out.println("Bitte geben sie einen Betrag ein.");
			System.out.println("---------------------------------------------------");
		} else {
			double disbursedAmount = this.api.disburse(amount);
			if (disbursedAmount == 0.0) {
				System.out.println("---------------------------------------------------");
				System.out.println(amount + " konnte nicht ausbezahlt werden.");
				System.out.println("Account Saldo betraegt: " + api.getAccountBalance());
				System.out.println("---------------------------------------------------");
			} else {
				System.out.println("---------------------------------------------------");
				System.out.println(amount + " ausbezahlt");
				System.out.println("Neuer Account Saldo betraegt: " + api.getAccountBalance());
				System.out.println("---------------------------------------------------");
			}
		}
	}

	public void getMarketPrice(String isinNo) {
		double marketPrice = this.api.getMarketPrice(isinNo);
		if (marketPrice == 0.0) {
			System.out.println("---------------------------------------------------");
			System.out.println("Marktpreis der " + isinNo + " konnte nicht ausgelesen werden.");
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("Marktpreis der " + isinNo + " betraegt: " + marketPrice);
			System.out.println("---------------------------------------------------");
		}
	}

	public void buyShare(String isinNo) {
		if (this.api.buyShare(isinNo)) {
			System.out.println("---------------------------------------------------");
			System.out.println("Job erstellt um Aktie " + isinNo + " zu kaufen.");
			System.out.println(this.api.runJobs());
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("Aktie mit isinNo " + isinNo + " konnte nicht gekauft werden.");
			System.out.println("---------------------------------------------------");
		}
	}

	public void sellShare(String isinNo) {
		String sold = this.api.sellShare(isinNo);
		if (sold.equals("Aktie " + isinNo + " verkauft.")) {
			System.out.println("---------------------------------------------------");
			System.out.println("Job erstellt um Aktie " + isinNo + " zu verkaufen.");
			System.out.println(this.api.runJobs());
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println(sold);
			System.out.println("---------------------------------------------------");
		}	
	}

	public void defineLimitBuy(String isinNo, double limit) {
		if (this.api.defineLimitBuy(isinNo, limit)) {
			System.out.println("---------------------------------------------------");
			System.out.println("Aktie kaufen mit Limitpreis von: " + limit);
			System.out.println(this.api.runJobs());
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("Aktie konnte nicht gekauft werden mit Limitpreis von: " + limit);
			System.out.println("---------------------------------------------------");			
		}
	}

	public void defineLimitSell(String isinNo, double limit) {
		if (this.api.defineLimitSell(isinNo, limit)) {
			System.out.println("---------------------------------------------------");
			System.out.println("Aktie verkaufen mit Limitpreis von: " + limit);
			System.out.println(this.api.runJobs());
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("Aktie konnte nicht verkauft werden mit Limitpreis von: " + limit);
			System.out.println("---------------------------------------------------");			
		}
	}

	public void calculateWinOrLoss() {
		double currentWinOrLoss = this.api.calculateWinOrLoss();
		if (currentWinOrLoss == 0.0) {
			System.out.println("---------------------------------------------------");
			System.out.println("Aktueller Verlust/Gewinn: " + api.calculateWinOrLoss());
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("Fehler: Aktueller Verlust/Gewinn konnte nicht berechnet werden.");
			System.out.println("---------------------------------------------------");
		}
	}

	public void printJobs() {
		System.out.println("---------------------------------------------------");
		System.out.println("pendente Jobs:\n" + api.printJobs());
		System.out.println("---------------------------------------------------");
	}

	public void removeJobs(int jobId) {
		String ret = this.api.removeJob(jobId);
		if (ret.equals("Job mit ID " + jobId + " wurde entfernt.")) {
			System.out.println("---------------------------------------------------");
			System.out.println("pendenter Jobs geloescht: " + jobId);
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("pendenter Jobs konnte nicht geloescht werden : " + jobId);
			System.out.println("---------------------------------------------------");
		}		
	}

	public void printShares() {
		System.out.println("---------------------------------------------------");
		System.out.println("Shares in meinem Depot:\n" + api.printShares());
		System.out.println("---------------------------------------------------");
	}
}
