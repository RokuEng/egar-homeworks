package com.RokuEng.homeworks.les2.domains.events;

import com.RokuEng.homeworks.les2.domains.Event;
import com.RokuEng.homeworks.les2.domains.entities.Content;
import lombok.Getter;

public abstract class SimpleEvent<T extends Content> implements Event {

	@Getter
	protected T content;

	public SimpleEvent(T content) {
		this.content = content;
	}
}
