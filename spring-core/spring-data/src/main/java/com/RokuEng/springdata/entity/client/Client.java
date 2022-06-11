package com.RokuEng.springdata.entity.client;

import com.RokuEng.springdata.entity.Person;
import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.enumerable.CreditHistory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends Person {

	@Column(name = "credit_history")
	@Enumerated(EnumType.ORDINAL)
	private CreditHistory creditHistory;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "client_person",
		joinColumns = @JoinColumn(name = "client_id"),
		inverseJoinColumns = @JoinColumn(name = "person_id")
	)
	private List<Person> family;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Account> accounts;
}
