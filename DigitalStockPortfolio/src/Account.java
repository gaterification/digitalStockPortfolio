import javax.security.auth.login.AccountException;

public class Account {
	// class attributes
	private static Account account; // singleton
	private static int trxNumber = 0;

	// attributes
	private int accountNumber;
	private double accountBalance;
	private CustodyAccount custodyAccount;

	// construct
	private Account() {
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

	public double disburse(double amount) {
		if (accountBalance < amount) {
			//throw new AccountException ("Betrag ist grösser als Saldo");
		} else {
			accountBalance = accountBalance - amount;

		}
		return accountBalance;
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
