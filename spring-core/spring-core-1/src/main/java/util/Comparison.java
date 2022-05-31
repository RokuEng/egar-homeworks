package util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class Comparison {
	public boolean biggerOrEqual(BigDecimal a, BigDecimal b) {
		switch (a.compareTo(b)) {
			case 0:
			case 1:
				return true;
			default:
				return false;
		}
	}

	public boolean lessOrEqual(BigDecimal a, BigDecimal b) {
		switch (a.compareTo(b)) {
			case -1:
			case 0:
				return true;
			default:
				return false;
		}
	}
}
