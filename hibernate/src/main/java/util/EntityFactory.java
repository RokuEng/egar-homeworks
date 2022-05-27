package util;

import entity.employee.Employee;
import entity.employee.State;
import entity.job.Job;
import entity.person.Gender;
import entity.person.Person;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@UtilityClass
public class EntityFactory {

	private final String[] names = {"John", "Tiler", "Alexandr", "Kate", "Fill", "Konstantin", "Connor", "Murad", "Alice", "Alina"};
	private final String[] lastnames = {"Smith", "Derden", "Will", "Nora", "Karaev"};
	private final Gender[] genders = Gender.values();
	private final BigDecimal[] salary = {salary(500d), salary(1000d), salary(2000d), salary(4000d), salary(8000d)};
	private final String[] job_names = {"Software Engineer", "Data Scientist", "Machine Learning Engineer", "Cleaner", "Artist"};

	public Person person() {
		Person person = new Person();

		person.setAge(new Random().nextInt(100));
		person.setFirstname(random(names));
		person.setLastname(random(lastnames));
		person.setGender(random(genders));

		return person;
	}

	public Employee employee() {
		Employee employee = new Employee();

		employee.setSalary(random(salary));
		employee.setFriends(list(EntityFactory::person));
		employee.setPerson(person());

		return employee;
	}

	public Job job() {
		Job job = new Job();

		job.setName(random(job_names));

		return job;
	}

	public State state() {
		State state = new State();
		System.out.println(state);
		return state;
	}

	private  <T> T random(T[] e) {
		return e[new Random().nextInt(e.length)];
	}

	private BigDecimal salary(Double value) {
		return BigDecimal.valueOf(value);
	}

	private <T> List<T> list(Supplier<T> func) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < new Random().nextInt(10); i++) {
			list.add(func.get());
		}
		return list;
	}

	private Employee job_employee() {
		Employee employee = new Employee();

		employee.setSalary(random(salary));
		employee.setFriends(list(EntityFactory::person));
		employee.setPerson(person());

		return employee;
	}
}
