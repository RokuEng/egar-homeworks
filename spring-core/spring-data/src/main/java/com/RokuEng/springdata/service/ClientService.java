package com.RokuEng.springdata.service;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.account.CreditAccount;
import com.RokuEng.springdata.entity.client.Client;
import com.RokuEng.springdata.factory.AccountFactory;
import com.RokuEng.springdata.repo.custom.EagerClientRepo;
import com.RokuEng.springdata.repo.spring.AccountRepo;
import com.RokuEng.springdata.repo.spring.ClientRepo;
import com.RokuEng.springdata.repo.spring.CreditAccountRepo;
import com.RokuEng.springdata.repo.spring.SharedAccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class ClientService {

	private CreditAccountRepo creditAccountRepo;
	private AccountRepo accountRepo;
	private SharedAccountRepo sharedAccountRepo;

	private EagerClientRepo eagerClientRepo;
	private ClientRepo clientRepo;

	private AccountFactory<Account> commonRubAccountFactory;
	private AccountFactory<CreditAccount> creditRubAccountFactory;

	public void save(Client client) {
		clientRepo.save(client);
	}

	public Optional<Client> findById(Integer id) {
		return eagerClientRepo.findByIdWithFamilyAndAccounts(id);
	}

	public void openCommonRUBAccount(Client client) {
		Account account = commonRubAccountFactory.get();
		openAccounts(client, account);
	}

	public void openCreditRUBAccount(Client client) {
		CreditAccount creditAccount = creditRubAccountFactory.get();
		openAccounts(client,creditAccount);
	}

	private void openAccounts(Client client, Account... accounts) {
		Arrays.stream(accounts).forEach(a -> {
			a.setOwner(client);
			client.getAccounts().add(a);
		});
	}
}
