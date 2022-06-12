package com.RokuEng.springdata.entity.account;

import com.RokuEng.springdata.entity.Persistent;
import com.RokuEng.springdata.entity.client.Client;
import com.RokuEng.springdata.entity.embeddable.Currency;
import com.RokuEng.springdata.entity.enumerable.CurrencyType;
import com.RokuEng.springdata.factory.CurrencyFactory;
import lombok.Data;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends Persistent<Long> implements CurrencyHolder {

	@ManyToOne(fetch = FetchType.EAGER)
	@ToString.Exclude
	private Client owner;

	@Embedded
	@Column(nullable = false)
	private Currency currency;

	@PostConstruct
	public void postConstruct() {
		if (currency == null) {
			currency = CurrencyFactory.of(0, CurrencyType.RUB);
		}
	}

	public void putOnAccount(Currency currency) {
		if (canApply(currency)) {
			apply(currency);
		}
	}

	public void putOnAccount(BigDecimal money) {
		if (canApply(money)) {
			apply(money);
		}
	}

	@Override
	public void apply(Currency currency) {
		if (canApplyCurrencyType(currency)) {
			this.currency = currency;
		}
	}

	@Override
	public void apply(BigDecimal bigDecimal) {
		BigDecimal currentMoney = currency.getAmount();
		currency.setAmount(currentMoney.add(bigDecimal));
	}
}
