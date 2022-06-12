package com.RokuEng.springdata.entity.client;

import com.RokuEng.springdata.entity.Person;
import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.enumerable.CreditHistory;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends Person {

	@Column(name = "credit_history")
	@Enumerated(EnumType.ORDINAL)
	private CreditHistory creditHistory = CreditHistory.UNKNOWN;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "client_person",
		joinColumns = @JoinColumn(name = "client_id"),
		inverseJoinColumns = @JoinColumn(name = "person_id")
	)
	private Set<Person> family = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Account> accounts = new ArrayList<>();
}
