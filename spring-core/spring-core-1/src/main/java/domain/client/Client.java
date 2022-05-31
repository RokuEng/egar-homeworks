package domain.client;

import domain.account.Account;

import java.math.BigDecimal;

public interface Client {
	Account getAccount();
	void income();

	default BigDecimal getIncome(BigDecimal money, BigDecimal multiply) {
		return money.multiply(multiply).subtract(money);
	}
}
