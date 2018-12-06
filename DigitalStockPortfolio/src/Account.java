
public class Account {
	// class attributes
	private static Account account;	// singleton
	private static int trxNumber = 0;
	
	// attributes
	private int accountNumber;
	private double accountBalance;
	private CustodyAccount custodyAccount;
	
	// construct
	private Account( ) {
		this.accountNumber = trxNumber;
		trxNumber = trxNumber + 1;
		this.accountBalance = 0.0;
		this.custodyAccount = CustodyAccount.getCustodyAccount(this); // TODO: SINGLETON
	}
	
	// methods
	public static Account getAccount() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}
	
	public double disburse() {
		// TODO: implement
		return 0.0;
	}
	
	public void deposit(double amount) {
		// TODO: implement
	}
	
	public double getAccountBalance() {
		return this.accountBalance;
	}
	
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	public CustodyAccount getCustodyAccount() {
		return this.custodyAccount;
	}
}
