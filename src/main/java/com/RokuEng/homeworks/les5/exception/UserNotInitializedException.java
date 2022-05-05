package com.RokuEng.homeworks.les5.exception;

import com.RokuEng.homeworks.les5.domain.User;
import lombok.extern.java.Log;

@Log
public class UserNotInitializedException extends Exception {
	public UserNotInitializedException(User user) {
		super();
		log.warning(user.toString() + " not implemented correctly");
	}

	public UserNotInitializedException(User user, String message) {
		super(message);
		log.warning(user.toString() + " not implemented correctly");
	}

	public UserNotInitializedException(User user, String message, Throwable cause) {
		super(message, cause);
		log.warning(user.toString() + " not implemented correctly");
	}

	public UserNotInitializedException(User user, Throwable cause) {
		super(cause);
		log.warning(user.toString() + " not implemented correctly");
	}
}
