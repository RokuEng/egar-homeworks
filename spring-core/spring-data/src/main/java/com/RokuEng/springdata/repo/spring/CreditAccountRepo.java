package com.RokuEng.springdata.repo.spring;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.account.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditAccountRepo extends JpaRepository<CreditAccount, Long> {

	@Query("SELECT a FROM CreditAccount a WHERE a.currency.amount > 0")
	List<CreditAccount> findByDutyNotZero();

}
