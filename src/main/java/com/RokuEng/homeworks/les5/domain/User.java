package com.RokuEng.homeworks.les5.domain;

import com.RokuEng.homeworks.les5.exception.UserNotInitializedException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@Log
@ToString
public class User {

	@Setter
	private Long id;
	@Setter
	private String username;
	@Setter
	private String firstname = "Firstname";
	@Setter
	private String lastname = "Lastname";
	private boolean active = false;

	public void active() throws UserNotInitializedException {
		if (id == null || username == null) {
			throw new UserNotInitializedException(this);
		}

		active = true;
		log.info(toString() + " now is active");
	}
}
