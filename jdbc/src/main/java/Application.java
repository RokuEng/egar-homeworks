import dao.Dao;
import dao.DaoImpl;

import java.sql.ResultSet;

public class Application {
    public static void main(String[] args) {
        Dao<Object, Integer> dao = new DaoImpl();

        Integer id = dao.usePreparedStatement("SELECT * FROM Coffee WHERE id = 0", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.getInt("id");
        });

        System.out.println(id);
    }
}
