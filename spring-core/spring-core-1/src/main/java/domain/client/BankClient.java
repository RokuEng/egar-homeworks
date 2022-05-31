package domain.client;

import domain.account.Account;
import domain.account.ClientAccount;
import org.springframework.beans.factory.annotation.Autowired;

public class BankClient implements Client {

	private Account account;

	@Autowired
	public BankClient(Account account) {
		this.account = account;
	}

	@Override
	public Account getAccount() {
		return account;
	}

	@Override
	public void income() {
		account.addMoney(
			getIncome(account.getMoney(), account.getProgram().getIncomingModifier())
		);
	}

//	@Autowired
//	public void setAccount(Account account) {
//		this.account = account;
//	}
}
