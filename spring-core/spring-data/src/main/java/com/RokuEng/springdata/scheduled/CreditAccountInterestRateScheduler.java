package com.RokuEng.springdata.scheduled;

import com.RokuEng.springdata.aspect.InterestRateApplyAspect;
import com.RokuEng.springdata.aspect.ProceedTimeAspect;
import com.RokuEng.springdata.entity.account.CreditAccount;
import com.RokuEng.springdata.service.AccountService;
import com.RokuEng.springdata.util.Transactions;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditAccountInterestRateScheduler {

	private static final String APPLY_CRON_RATE = "5 * * * * *";

	private AccountService accountService;

	@Scheduled(cron = APPLY_CRON_RATE)
	@InterestRateApplyAspect
	@ProceedTimeAspect
	public void apply() {
		Transactions.transaction(this::applyInterestRate);
	}

	private void applyInterestRate() {
		accountService.findDebtorsAccounts().forEach(CreditAccount::applyRate);
		accountService.findDebtorsAccounts().forEach(accountService::save);
	}
}
