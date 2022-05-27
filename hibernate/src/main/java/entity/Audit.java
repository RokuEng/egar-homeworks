package entity;

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
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@PrePersist
	public void create() {
		createDate = LocalDateTime.now();
	}

	@PreUpdate
	public void update() {
		updateDate = LocalDateTime.now();
	}
}
