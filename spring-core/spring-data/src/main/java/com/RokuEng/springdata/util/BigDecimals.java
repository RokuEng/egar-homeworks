package com.RokuEng.springdata.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum BigDecimals {
	HUNDRED(BigDecimal.valueOf(100))
	;

	private final BigDecimal value;
}
