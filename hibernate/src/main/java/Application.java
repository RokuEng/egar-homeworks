import dao.EmployeeDao;
import dao.PersonDao;
import util.PersonGenerator;

public class Application {
	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();

		for (int i = 0; i < 100; i++) {
			dao.save(PersonGenerator.employee());
		}

		System.out.println(dao.findAll().get(0));
	}
}
