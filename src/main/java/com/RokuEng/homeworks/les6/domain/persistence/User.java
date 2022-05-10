package com.RokuEng.homeworks.les6.domain.persistence;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Entity
@Data
public class User extends Person {

	@ManyToMany
	private Set<Person> friends;

	private String firstname;

	private String lastname;

	public User() {
	}

	public User(String nickname, Set<Person> friends, String firstname, String lastname) {
		super(nickname);
		this.friends = friends;
		this.firstname = firstname;
		this.lastname = lastname;
	}
}
