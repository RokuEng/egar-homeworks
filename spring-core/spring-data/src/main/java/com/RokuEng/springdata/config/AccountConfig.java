package com.RokuEng.springdata.config;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.account.CreditAccount;
import com.RokuEng.springdata.factory.AccountFactory;
import com.RokuEng.springdata.util.BeanScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AccountConfig {

	@Bean
	@Scope(BeanScope.prototype)
	public Account account() {
		return new Account();
	}

	@Bean
	@Scope(BeanScope.prototype)
	public Account creditAccount() {
		return new CreditAccount();
	}

	@Bean(name = "commonRubAccountFactory")
	public AccountFactory accountFactory() {
		return this::account;
	}

	@Bean(name = "creditRubAccountFactory")
	public AccountFactory creditAccountFactory() {
		return this::creditAccount;
	}
}
