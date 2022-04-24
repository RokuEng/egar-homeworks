package com.RokuEng.homeworks.les2.domains.entities;

import com.RokuEng.homeworks.les2.domains.traits.Persistent;
import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class Content implements Persistent {

	@Getter
	protected int id;

	public String getContent() {
		throw new UnsupportedOperationException();
	}

	public Content(int id) {
		this.id = id;
	}
}
