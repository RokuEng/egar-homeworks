package com.RokuEng.springdata.entity;

import com.RokuEng.springdata.entity.embeddable.Audit;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class Persistent<ID extends Serializable> {
	@Id
	@Column(name = "id")
	private ID id;

	@Embedded
	private Audit audit = new Audit();
}
