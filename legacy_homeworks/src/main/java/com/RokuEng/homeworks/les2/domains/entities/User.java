package com.RokuEng.homeworks.les2.domains.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User extends Content {

	@Getter
	@Setter
	private String nickname;

	@Getter
	@Setter
	private String updates;

	@Override
	public String getContent() {
		return updates;
	}

	public User(int id, String nickname, String updates) {
		super(id);
		this.nickname = nickname;
		this.updates = updates;
	}
}
