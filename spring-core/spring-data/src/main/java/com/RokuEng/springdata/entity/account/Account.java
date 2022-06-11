package com.RokuEng.springdata.entity.account;

import com.RokuEng.springdata.entity.Persistent;
import com.RokuEng.springdata.entity.client.Client;
import com.RokuEng.springdata.entity.embeddable.Currency;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends Persistent<Long> {

	@ManyToOne(fetch = FetchType.EAGER)
	private Client owner;

	@Embedded
	private Currency currency;
}
