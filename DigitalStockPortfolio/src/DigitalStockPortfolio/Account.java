package DigitalStockPortfolio;
public class Account {
	// class attributes
	private static Account account;
	private static int trxNumber = 0;

	// attributes
	private int accountNumber;
	private double accountBalance;
	private CustodyAccount custodyAccount;

	// construct
	private Account() {
		Account.trxNumber = Account.trxNumber + 1;
		this.accountNumber = trxNumber;
		this.accountBalance = 0.0;
	}

	// methods
	public static Account getAccount() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}
	
	public void addCustodyAccount(StockExchange stockExchange) {
		this.custodyAccount = new CustodyAccount(this, stockExchange);
	}

	public double disburse(double amount) throws AccountException {
		if (accountBalance < amount) {
			throw new AccountException("Zu wenig Geld um " + amount + " abzuheben.");
		} else {
			accountBalance = accountBalance - amount;
		}
		return amount;
	}

	public void deposit(double amount) {
		accountBalance = accountBalance + amount;
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