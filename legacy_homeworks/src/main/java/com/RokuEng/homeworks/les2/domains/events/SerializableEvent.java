package com.RokuEng.homeworks.les2.domains.events;

import com.RokuEng.homeworks.les2.domains.entities.Content;
import com.RokuEng.homeworks.les2.domains.traits.Serializable;

public class SerializableEvent<T extends Content> extends SimpleEvent implements Serializable {
	@Override
	public String serialize() {
		return "ID: " + getContent().getId() + "\nContent: " + getContent().getContent();
	}

	public SerializableEvent(Content content) {
		super(content);
	}
}
