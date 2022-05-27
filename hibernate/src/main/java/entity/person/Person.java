package entity.person;

import entity.Audit;
import entity.Persistent;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person implements Persistent<Integer> {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "gender_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	@Embedded
	private Audit audit = new Audit();
}
