package com.RokuEng.homeworks.les2.domains;

public interface Event<T> {
	default T getContent() {
		throw new UnsupportedOperationException();
	}

	default void setContent() {
		throw new UnsupportedOperationException();
	}
}
