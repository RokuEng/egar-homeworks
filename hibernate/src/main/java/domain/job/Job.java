package domain.job;

import domain.Persistent;
import domain.employee.Employee;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Job implements Persistent<Integer> {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@ToString.Exclude
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "director_id", nullable = false)
	private Employee director;

	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Employee> workers = new ArrayList<>();
}
