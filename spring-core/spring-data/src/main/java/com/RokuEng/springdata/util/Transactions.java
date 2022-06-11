package com.RokuEng.springdata.util;

import lombok.experimental.UtilityClass;

import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public class Transactions {
	public void transaction(Runnable function) {
		try {
			function.run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
