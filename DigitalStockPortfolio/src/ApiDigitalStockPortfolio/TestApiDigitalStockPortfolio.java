package ApiDigitalStockPortfolio;

//import java.util.ArrayList;
// TODO: both not needed!!
//import DigitalStockPortfolio.Job;
//import DigitalStockPortfolio.Share;

public class TestApiDigitalStockPortfolio {
	private ApiDigitalStockPortfolio api;
	
	public static void main(String[] args) {
		// TODO: @all: moeglichkeiten dokumentieren!!
		ApiDigitalStockPortfolio api = new ApiDigitalStockPortfolio();
		api.sellShare("TSLA");
		api.runJobs();
/*		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}*/

//		System.out.println("Account-Nummer, sollte 1 sein: " + api.getAccountNumber());
//		System.out.println("CustodyAccount-Nummer, sollte 1 sein: " + api.getCustodyAccountNumber());
//		System.out.println("Account-Saldo, sollte 0.0 sein: " + api.getAccountBalance());
		api.deposit(10000.0);
//		System.out.println("Account-Saldo, sollte 1000.0 sein: " + api.getAccountBalance());
		api.disburse(500.0);
//		System.out.println("Account-Saldo, sollte 500.0 sein: " + api.getAccountBalance());
//		System.out.println("Aktueller Preis von TSLA: " + api.getMarketPrice("TSLA"));
//		System.out.println("Aktueller winOrLos: Sollte ein Fehler ausgeben, da noch keine Aktien vorhanden.");
//		System.out.println("Aktuelle Aktien, sollte ein leeres Array sein: " + api.getShares());
//		System.out.println("Aktuelle Jobs, sollte ein leeres Array sein: " + apiTest.printJobs(api.getJobs()));
//		api.sellShare("TSLA");
		System.out.println(api.getAccountBalance());
		api.buyShare("TSLA");
		api.buyShare("PG");
		api.buyShare("UNP");
		api.buyShare("ABBV");
		api.buyShare("INTC");
		api.buyShare("AAPL");
		api.buyShare("ABT");
		api.buyShare("HAL");
		api.buyShare("COF");
		api.buyShare("FDEF");
		System.out.println(api.printJobs());
		//api.removeJobs(1);
		//api.removeJobs(2);
		System.out.println(api.getMarketPrice("TSLA") + "|" + 
		api.getMarketPrice("PG") + "|" + 
				api.getMarketPrice("UNP") + "|" + 
		api.getMarketPrice("ABBV") + "|" + 
				api.getMarketPrice("INTC") + "|" + 
		api.getMarketPrice("AAPL") + "|" + 
				api.getMarketPrice("ABT") + "|" +
				api.getMarketPrice("HAL") + "|" +
				api.getMarketPrice("COF") + "|" +
		api.getMarketPrice("TSLA"));
//		apiTest.printJobs(api.getJobs());
		api.runJobs();
		//for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			System.out.println(api.getAccountBalance());
		System.out.println(api.printShares());
			System.out.println(api.calculateWinOrLoss());
			System.out.println(api.getMarketPrice("TSLA") + "|" + 
					api.getMarketPrice("PG") + "|" + 
							api.getMarketPrice("UNP") + "|" + 
					api.getMarketPrice("ABBV") + "|" + 
							api.getMarketPrice("INTC") + "|" + 
					api.getMarketPrice("AAPL") + "|" + 
							api.getMarketPrice("ABT") + "|" +
							api.getMarketPrice("HAL") + "|" +
							api.getMarketPrice("COF") + "|" +
					api.getMarketPrice("TSLA"));
		//}
		System.out.println("10 Sekunden warten");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
//		apiTest.printShares(api.getShares());
		System.out.println(api.getAccountBalance());
		api.sellShare("AAPL");
		api.sellShare("AAPL");
		api.sellShare("AAPL");
		api.buyShare("TSLA");
		api.buyShare("TSLA");
		api.buyShare("TSLA");
		api.sellShare("AAPL");
		api.sellShare("AAPL");
		
		//apiTest.printJobs(api.getJobs());
		//api.runJobs();
		System.out.println("10 Sekunden warten");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(api.getAccountBalance());
//		apiTest.printShares(api.getShares());
/*		api.buyShare("TSLA");
		api.buyShare("TSLA");
		api.runJobs();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}

		api.definedLimitSell("TSLA", 340.0);
		api.definedLimitSell("TSLA", 360.0);
		api.runJobs();*/
		System.out.println(api.calculateWinOrLoss());
	}
	
	public TestApiDigitalStockPortfolio() {
		this.api = new ApiDigitalStockPortfolio();
	}
	
	// getters/setters
	public ApiDigitalStockPortfolio getApi() {
		return this.api;
	}
}
/*	private void printJobs(ArrayList<Job> jobs) {
		String jobString = "";
		ArrayList<String> jobsAsString = new ArrayList<String>();
		if (jobs.size() > 0) {
			for (Job job : jobs) {
				String limitAsString = "";
				if (job.getLimit() > 0.0) {
					limitAsString = ", Limit:" + job.getLimit();
				}
				jobsAsString.add("ID: " + Integer.toString(job.getId()) + ", Typ: " + job.getJobType() + ", isinNo: " + job.getIsinNo() + limitAsString);
			}
			jobString = "Jobs: " + String.join(" | ", jobsAsString);
		} else {
			jobString =  "Keine Jobs vorhanden die ausgegeben werden können.";
		}
		System.out.println(jobString);
	}*/
	
/*	private void printShares(ArrayList<Share> shares) {
		String shareString = "";
		ArrayList<String> sharesAsString = new ArrayList<String>();
		if (shares.size() > 0) {
			for (Share share : shares) {
				sharesAsString.add("Aktien: Name: " + share.getName() + ", IsinNo: " + share.getIsinNo() + ", gekauft für: " + share.getCostPrice());
			}
			shareString = "Auflistung Aktien: " + String.join( " | ", sharesAsString);
		} else {
			shareString = "Keine Aktien vorhanden die ausgegeben werden können.";
		}
		System.out.println(shareString);
	}
}*/
