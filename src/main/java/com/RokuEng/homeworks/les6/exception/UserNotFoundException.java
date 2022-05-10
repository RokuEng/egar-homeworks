package com.RokuEng.homeworks.les6.exception;

import com.RokuEng.homeworks.les6.util.PersonFormater;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends PersonNotFoundException {
	public UserNotFoundException(Long id) {
		super(PersonFormater.formatExceptionMessage("User with specific id cannot be founded", id));
	}
}
