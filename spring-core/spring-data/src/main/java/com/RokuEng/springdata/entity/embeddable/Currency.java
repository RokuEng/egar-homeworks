package com.RokuEng.springdata.entity.embeddable;

import com.RokuEng.springdata.entity.enumerable.CurrencyType;
import com.RokuEng.springdata.exception.CurrencyNotInitializedException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.math.BigDecimal;

@Embeddable
@Data
@Slf4j
public class Currency {
	@Column(name = "money", nullable = false)
	private BigDecimal amount;

	@Column(name = "currency_type", nullable = false)
	private CurrencyType type;

	@PrePersist
	public void create() {
		if (amount == null) {
			amount = BigDecimal.ZERO;
			log.warn("Persist Currency has null amount of money");
		}
		if (type == null) {
			throw new CurrencyNotInitializedException();
		}
	}
}
