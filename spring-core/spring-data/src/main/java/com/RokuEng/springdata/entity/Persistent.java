package com.RokuEng.springdata.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class Persistent<ID extends Serializable> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private ID id;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "update_date")
	private LocalDateTime updatedAt;

	@PrePersist
	public void create() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void update() {
		updatedAt = LocalDateTime.now();
	}
}
