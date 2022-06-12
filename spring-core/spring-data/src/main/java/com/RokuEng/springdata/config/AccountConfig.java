package com.RokuEng.springdata.config;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.account.CreditAccount;
import com.RokuEng.springdata.entity.embeddable.Currency;
import com.RokuEng.springdata.entity.enumerable.CurrencyType;
import com.RokuEng.springdata.factory.AccountFactory;
import com.RokuEng.springdata.factory.CurrencyFactory;
import com.RokuEng.springdata.util.BeanScope;
import com.RokuEng.springdata.util.BigDecimals;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.math.BigDecimal;

@Configuration
public class AccountConfig {

	@Bean
	@Scope(BeanScope.prototype)
	public Account account() {
		return new Account();
	}

	@Bean
	@Scope(BeanScope.prototype)
	public CreditAccount creditAccount() {
		CreditAccount creditAccount = new CreditAccount();
		creditAccount.setCurrency(CurrencyFactory.of(0, CurrencyType.RUB));
		creditAccount.setPercentage(BigDecimal.valueOf(20d));
		return creditAccount;
	}

	@Bean(name = "commonRubAccountFactory")
	public AccountFactory<Account> accountFactory() {
		return this::account;
	}

	@Bean(name = "creditRubAccountFactory")
	public AccountFactory<CreditAccount> creditAccountFactory() {
		return this::creditAccount;
	}
}
