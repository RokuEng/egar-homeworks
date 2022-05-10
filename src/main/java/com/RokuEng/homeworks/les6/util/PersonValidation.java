package com.RokuEng.homeworks.les6.util;

import com.RokuEng.homeworks.les6.domain.persistence.Person;
import com.RokuEng.homeworks.les6.domain.persistence.User;
import com.RokuEng.homeworks.les6.exception.IllegalPersonStateException;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class PersonValidation {

	public <T extends Person> boolean check(T person) {

		if (person.getId() <= 0) {
			throw new IllegalPersonStateException(person);
		}

		if (person.getNickname() == null) {
			return false;
		}

		return true;
	}

	public <T extends User> boolean check(T user) {

		if (!check((Person) user)) {
			return false;
		}

		if (user.getFirstname() == null) {
			return false;
		}

		if (user.getLastname() == null) {
			return false;
		}

		if (user.getFriends() == null) {
			user.setFriends(new HashSet<>());
		}

		return true;
	}
}
