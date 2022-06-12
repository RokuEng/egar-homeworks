package com.RokuEng.springdata.entity;

import com.RokuEng.springdata.entity.embeddable.Audit;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class Persistent<ID extends Serializable> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private ID id;

	@Embedded
	@Cascade(CascadeType.PERSIST)
	private Audit audit = new Audit();
}
