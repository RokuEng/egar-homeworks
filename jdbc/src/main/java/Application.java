import dao.CoffeeDao;
import entity.CoffeeEntity;
import exception.SQLConnectionException;

import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws SQLConnectionException {
        CoffeeDao dao = new CoffeeDao();

    }
}
