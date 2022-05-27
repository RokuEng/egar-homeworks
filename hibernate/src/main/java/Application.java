import dao.EmployeeDao;
import dao.StateDao;
import entity.employee.Employee;
import entity.employee.State;
import entity.job.Job;
import util.EntityFactory;

import java.util.Arrays;

public class Application {
	public static void main(String[] args) {

		EmployeeDao dao = new EmployeeDao();
		dao.save(EntityFactory.employee());
		Employee employee = dao.findById(1).orElseThrow();
		System.out.println(employee);

	}
}
