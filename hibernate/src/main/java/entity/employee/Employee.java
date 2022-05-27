package entity.employee;

import entity.Persistent;
import entity.job.Job;
import entity.person.Person;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Employee implements Persistent<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;

	@Column(name = "salary", nullable = false)
	private BigDecimal salary;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "state_id")
	private State state;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
		name = "job_employee",
		joinColumns = @JoinColumn(name = "workers_id"),
		inverseJoinColumns = @JoinColumn(name = "job_id")
	)
	private List<Job> jobs = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
		name = "employee_person",
		joinColumns = @JoinColumn(name = "friends_id"),
		inverseJoinColumns = @JoinColumn(name = "employee_id")
	)
	private List<Person> friends = new ArrayList<>();
}
