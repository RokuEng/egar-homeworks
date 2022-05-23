import dao.OrganisationDao;
import exception.CoffeeNotFoundException;
import exception.SQLConnectionException;

public class Application {
	public static void main(String[] args)  {
		OrganisationDao dao = new OrganisationDao();
		dao.delete(dao.findById(1).orElseThrow(CoffeeNotFoundException::new));
		System.out.println(dao.findById(1).get());
	}
}
