package com.RokuEng.homeworks.les2.domains.traits;

import com.RokuEng.homeworks.les2.domains.Trait;

public interface Executable extends Trait {
	default String execute() {
		return "Execute: ";
	}
}
