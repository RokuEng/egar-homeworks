package com.RokuEng.springdata.entity.account;

import com.RokuEng.springdata.entity.client.Client;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class SharedAccount extends Account {

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "familyaccount_client",
		joinColumns = @JoinColumn(name = "family_account_id"),
		inverseJoinColumns = @JoinColumn(name = "client_id")
	)
	private List<Client> subOwners;

}
