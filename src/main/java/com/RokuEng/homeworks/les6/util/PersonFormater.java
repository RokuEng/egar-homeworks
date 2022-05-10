package com.RokuEng.homeworks.les6.util;

import com.RokuEng.homeworks.les6.domain.persistence.Person;
import com.RokuEng.homeworks.les6.domain.persistence.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonFormater {

	public String formatExceptionMessage(String message, long id) {
		return String.format(message + " { id: %d }", id);
	}

	public <T extends Person> StringBuilder formatInformation(T person) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s with id: %d \n nickname: %s ", person.getId(), person.getNickname()));
		return sb;
	}

	public <T extends User> StringBuilder formatInformation(T user) {
		StringBuilder sb = formatInformation((Person) user);
		sb.append(String.format("firstname: %s \n lastname: %s",user.getFirstname(),user.getLastname()));
		return sb;
	}
}
