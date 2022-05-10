package com.RokuEng.homeworks.les6.controller;

import com.RokuEng.homeworks.les6.domain.persistence.Person;
import com.RokuEng.homeworks.les6.domain.persistence.User;
import com.RokuEng.homeworks.les6.exception.PersonNotFoundException;
import com.RokuEng.homeworks.les6.exception.UserNotFoundException;
import com.RokuEng.homeworks.les6.repos.PersonRepo;
import com.RokuEng.homeworks.les6.repos.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
public class PersonController {

	private PersonRepo personRepo;

	public PersonController(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}

	@GetMapping
	public List<Person> home() {
		return personRepo.findAll();
	}

	@GetMapping("{id}")
	public Person person(
		@PathVariable long id
	) {
		return getPerson(id);
	}

	@PostMapping
	public Person create(
		@RequestBody Person person
		) {
		personRepo.save(person);
		return getPerson(person.getId());
	}

	private Person getPerson(long id) {
		System.out.println(id);
		return personRepo.findById(id).orElseThrow(() -> {
			return new PersonNotFoundException(String.valueOf(id));
		});
	}

}
