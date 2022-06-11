package com.RokuEng.springdata.entity.account;

import com.RokuEng.springdata.entity.embeddable.Currency;
import com.RokuEng.springdata.util.BigDecimals;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class CreditAccount extends Account {
	@Column(name = "interest_rate")
	private BigDecimal percentage;

	public void applyRate() {
		Currency currency = getCurrency();
		BigDecimal afterApply = currency.getAmount()
			.multiply(
				percentage.divide(BigDecimals.HUNDRED.getValue(), RoundingMode.DOWN)
			);
		currency.setAmount(afterApply);
	}
}
