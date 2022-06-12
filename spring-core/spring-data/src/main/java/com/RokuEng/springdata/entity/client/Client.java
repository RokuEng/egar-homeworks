package com.RokuEng.springdata.entity.client;

import com.RokuEng.springdata.entity.Person;
import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.enumerable.CreditHistory;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends Person {

	@Column(name = "credit_history")
	@Enumerated(EnumType.ORDINAL)
	private CreditHistory creditHistory = CreditHistory.UNKNOWN;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "client_person",
		joinColumns = @JoinColumn(name = "client_id"),
		inverseJoinColumns = @JoinColumn(name = "person_id")
	)
	private List<Person> family = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Account> accounts = new ArrayList<>();
}
