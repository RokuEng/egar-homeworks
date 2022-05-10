package com.RokuEng.homeworks.les6.domain.persistence;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Person {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String nickname;

	public Person() {
	}

	public Person(String nickname) {
		this.nickname = nickname;
	}
}
