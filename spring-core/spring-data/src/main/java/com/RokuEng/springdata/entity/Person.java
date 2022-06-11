package com.RokuEng.springdata.entity;

import com.RokuEng.springdata.entity.Persistent;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends Persistent<Integer> {
	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;
}
