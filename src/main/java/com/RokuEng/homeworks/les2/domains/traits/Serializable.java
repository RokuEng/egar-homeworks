package com.RokuEng.homeworks.les2.domains.traits;

import com.RokuEng.homeworks.les2.domains.Trait;

public interface Serializable {
	default String serialize() {
		throw new UnsupportedOperationException();
	}
}
