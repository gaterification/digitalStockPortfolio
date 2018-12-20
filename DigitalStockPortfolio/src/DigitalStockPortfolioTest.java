import java.util.ArrayList;

public class DigitalStockPortfolioTest {
	private Account account;
	private CustodyAccount custodyAccount;
	
	// TODO: separates package fuer Exceptions?
	// TODO: JUnit-Tests?
	public static void main(String[] args) {
		DigitalStockPortfolioTest tester = new DigitalStockPortfolioTest();
		tester.testAccount();
		tester.testCustodyAccount();
	}
	
	// construct
	public DigitalStockPortfolioTest() {
		this.account = Account.getAccount();
		this.custodyAccount = CustodyAccount.getCustodyAccount(this.account);
	}
	
	private void testAccount() {
		this.account.deposit(100);
		this.account.disburse(50);
		System.out.println("Pass: sollte 50 ausgeben: " + account.getAccountBalance());
		System.out.println("Fail: sollte Fehler ausgeben: " + account.disburse(100));
	}
	
	// TODO: biondi
	private void testCustodyAccount() {
		this.custodyAccount.buyShare("TSLA");
		ArrayList<Share> shares = this.custodyAccount.getShares();
		System.out.println("Pass: Sollte 0 sein: " + shares.size());
	}
	
	// TODO: nathan
	private void testJobWorker() {
		
	}
	
	// TODO: michel
	private void testNasdaq() {
		
	}
}
