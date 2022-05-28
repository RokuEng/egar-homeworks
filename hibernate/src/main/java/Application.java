import dao.EmployeeDao;
import dao.PersonDao;
import dao.StateDao;
import entity.employee.Employee;
import entity.employee.State;
import entity.job.Job;
import entity.person.Person;
import util.EntityFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Application {
	public static void main(String[] args) {

		EmployeeDao dao = new EmployeeDao();

		for (int i = 0; i < 100; i++) {
			dao.save(EntityFactory.employee());
		}

		List<Employee> employees = dao.findAll();

		System.out.println(employees);
	}
}
