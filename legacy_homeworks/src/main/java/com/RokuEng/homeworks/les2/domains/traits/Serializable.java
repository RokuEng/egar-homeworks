package com.RokuEng.homeworks.les2.domains.traits;

public interface Serializable {
	default String serialize() {
		throw new UnsupportedOperationException();
	}
}
