package entity.job;

import entity.Persistent;
import entity.employee.Employee;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Job implements Persistent<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@ToString.Exclude
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "director_id")
	private Employee director;

	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
		name = "job_employee",
		joinColumns = @JoinColumn(name = "job_id"),
		inverseJoinColumns = @JoinColumn(name = "workers_id")
	)
	private List<Employee> workers = new ArrayList<>();
}
