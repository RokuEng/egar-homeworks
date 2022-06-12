package com.RokuEng.springdata.entity.account;

import com.RokuEng.springdata.entity.embeddable.Currency;
import com.RokuEng.springdata.util.BigDecimals;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class CreditAccount extends Account {
	@Column(name = "interest_rate")
	private BigDecimal percentage;

	@Override
	public boolean canApply(Currency currency) {
		return getCurrency().getAmount().compareTo(currency.getAmount()) >= 0;
	}

	@Override
	public boolean canApply(BigDecimal decimal) {
		return getCurrency().getAmount().compareTo(decimal) >= 0;
	}

	@Override
	public void apply(Currency currency) {
		if (canApplyCurrencyType(currency)) {
			setCurrency(currency);
		}
	}

	@Override
	public void apply(BigDecimal bigDecimal) {
		BigDecimal currentMoney = getCurrency().getAmount();
		getCurrency().setAmount(currentMoney.subtract(bigDecimal));
	}

	public void applyRate() {
		System.out.println(this);

		Currency currency = getCurrency();
		BigDecimal afterApply = currency.getAmount()
			.multiply(
				BigDecimal.ONE.add(percentage.divide(BigDecimals.HUNDRED.getValue(), RoundingMode.DOWN))
			);
		currency.setAmount(afterApply);

		System.out.println(this);
	}
}
