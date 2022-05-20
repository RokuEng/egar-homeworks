package com.RokuEng.homeworks.les2.domains.traits;

public interface Persistent {
	default int getId() {
		throw new UnsupportedOperationException();
	}
}
