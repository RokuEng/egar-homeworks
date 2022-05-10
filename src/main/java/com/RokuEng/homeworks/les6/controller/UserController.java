package com.RokuEng.homeworks.les6.controller;

import com.RokuEng.homeworks.les6.domain.persistence.Person;
import com.RokuEng.homeworks.les6.domain.persistence.User;
import com.RokuEng.homeworks.les6.exception.UserNotFoundException;
import com.RokuEng.homeworks.les6.repos.UserRepo;
import com.RokuEng.homeworks.les6.util.PersonValidation;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

	private UserRepo userRepo;

	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping
	public List<User> home() {
		return userRepo.findAll().stream().filter(PersonValidation::check).collect(Collectors.toList());
	}

	@PostMapping
	public Person create(
		@RequestBody User user
	) {
		userRepo.save(user);
		return getUser(user.getId());
	}

	private User getUser(long id) {
		User user = userRepo.findById(id).orElseThrow(() -> {
			return new UserNotFoundException(id);
		});
		return user;
	}
}
