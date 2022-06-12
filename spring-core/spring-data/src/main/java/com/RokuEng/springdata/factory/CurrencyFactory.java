package com.RokuEng.springdata.factory;

import com.RokuEng.springdata.entity.embeddable.Currency;
import com.RokuEng.springdata.entity.enumerable.CurrencyType;
import com.sun.istack.NotNull;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class CurrencyFactory {
	public Currency of(
		@NotNull double amount,
		@NotNull CurrencyType type
	) {
		Currency currency = new Currency();
		currency.setAmount(BigDecimal.valueOf(amount));
		currency.setType(type);
		return currency;
	}
}
