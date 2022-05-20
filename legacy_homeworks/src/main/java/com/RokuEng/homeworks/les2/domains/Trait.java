package com.RokuEng.homeworks.les2.domains;

public interface Trait {
	default String trait() {
		throw new UnsupportedOperationException();
	}
}
