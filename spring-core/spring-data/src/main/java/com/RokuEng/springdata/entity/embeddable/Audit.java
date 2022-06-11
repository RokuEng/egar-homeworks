package com.RokuEng.springdata.entity.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Audit {
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
