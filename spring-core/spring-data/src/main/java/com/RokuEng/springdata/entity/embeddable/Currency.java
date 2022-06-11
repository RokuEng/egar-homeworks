package com.RokuEng.springdata.entity.embeddable;

import com.RokuEng.springdata.entity.enumerable.CurrencyType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
public class Currency {
	@Column(name = "money", nullable = false)
	private BigDecimal amount;

	@Column(name = "currency_type", nullable = false)
	private CurrencyType currency;
}
