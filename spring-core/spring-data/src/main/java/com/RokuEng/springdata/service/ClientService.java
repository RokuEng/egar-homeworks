package com.RokuEng.springdata.service;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.client.Client;
import com.RokuEng.springdata.factory.AccountFactory;
import com.RokuEng.springdata.repo.custom.EagerClientRepo;
import com.RokuEng.springdata.repo.spring.AccountRepo;
import com.RokuEng.springdata.repo.spring.ClientRepo;
import com.RokuEng.springdata.repo.spring.CreditAccountRepo;
import com.RokuEng.springdata.repo.spring.SharedAccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ClientService {

	private CreditAccountRepo creditAccountRepo;
	private AccountRepo accountRepo;
	private SharedAccountRepo sharedAccountRepo;

	private EagerClientRepo eagerClientRepo;
	private ClientRepo clientRepo;

	private AccountFactory commonRubAccountFactory;
	private AccountFactory creditRubAccountFactory;

	public void save(Client client) {
		clientRepo.save(client);
	}

	public void openCommonRUBAccount(Client client) {
		client.getAccounts().add(commonRubAccountFactory.get());
		clientRepo.save(client);
	}

	public void openCreditRUBAccount(Client client) {
		client.getAccounts().add(creditRubAccountFactory.get());
		clientRepo.save(client);
	}

	public void transferMoney(Account from, Account to, BigDecimal amount) {
		if (from.canApply(amount.negate()) && to.canApply(amount)) {
			from.apply(amount.negate());
			to.apply(amount);
		}
	}
}
