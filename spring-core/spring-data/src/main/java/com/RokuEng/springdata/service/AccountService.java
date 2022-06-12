package com.RokuEng.springdata.service;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.account.CreditAccount;
import com.RokuEng.springdata.entity.account.SharedAccount;
import com.RokuEng.springdata.entity.embeddable.Currency;
import com.RokuEng.springdata.repo.spring.AccountRepo;
import com.RokuEng.springdata.repo.spring.CreditAccountRepo;
import com.RokuEng.springdata.repo.spring.SharedAccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
	private CreditAccountRepo creditAccountRepo;
	private AccountRepo accountRepo;
	private SharedAccountRepo sharedAccountRepo;

	public List<CreditAccount> findDebtorsAccounts() {
		return creditAccountRepo.findByDutyNotZero();
	}

	public void save(CreditAccount account) {
		creditAccountRepo.save(account);
	}

	public void save(SharedAccount account) {
		sharedAccountRepo.save(account);
	}

	public void save(Account account) {
		accountRepo.save(account);
	}

	public void putOnDeposit(Account account, Currency currency) {
		account.putOnAccount(currency);
	}

	public void putOnDeposit(Account account, BigDecimal decimal) {
		account.putOnAccount(decimal);
	}
}
