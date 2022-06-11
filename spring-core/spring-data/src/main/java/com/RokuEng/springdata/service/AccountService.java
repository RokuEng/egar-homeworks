package com.RokuEng.springdata.service;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.account.CreditAccount;
import com.RokuEng.springdata.repo.spring.CreditAccountRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
	private CreditAccountRepo repo;

	public List<CreditAccount> findDebtorsAccounts() {
		return repo.findByDutyNotZero();
	}

	public void save(CreditAccount account) {
		repo.save(account);
	}
}
