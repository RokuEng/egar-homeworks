package entity.employee;

import entity.Persistent;
import entity.job.Job;
import entity.person.Person;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Employee implements Persistent<Integer> {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "salary", nullable = false)
	private BigDecimal salary;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Job> jobs = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Person> friends = new ArrayList<>();
}
