package com.RokuEng.homeworks.les2.domains.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Message extends Content {

	@Getter
	@Setter
	private String text;

	@Override
	public String getContent() {
		return text;
	}

	public Message(int id, String text) {
		super(id);
		this.text = text;
	}
}
