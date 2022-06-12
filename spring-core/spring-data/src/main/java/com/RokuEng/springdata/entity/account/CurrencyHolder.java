package com.RokuEng.springdata.entity.account;

import com.RokuEng.springdata.entity.embeddable.Currency;

import java.math.BigDecimal;

public interface CurrencyHolder {
	void apply(Currency currency);
	void apply(BigDecimal bigDecimal);
	Currency getCurrency();

	default boolean canApplyCurrencyType(Currency currency) {
		return this.getCurrency().getType() == currency.getType();
	}

	default boolean canApply(Currency currency) {
		return true;
	}

	default boolean canApply(BigDecimal decimal) {
		return true;
	}
}
