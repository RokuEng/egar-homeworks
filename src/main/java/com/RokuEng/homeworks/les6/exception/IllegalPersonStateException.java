package com.RokuEng.homeworks.les6.exception;

import com.RokuEng.homeworks.les6.domain.persistence.Person;
import lombok.Getter;

@Getter
public class IllegalPersonStateException extends RuntimeException {

	private Person person;
	public <T extends Person> IllegalPersonStateException(T person) {
		super();
		this.person = person;
	}
}
