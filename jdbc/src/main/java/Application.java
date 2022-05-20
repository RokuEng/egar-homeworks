import dao.Dao;
import dao.DaoImpl;
import exception.SQLConnectionException;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLConnectionException {

        DaoImpl dao = new DaoImpl();

        String name = dao.usePreparedStatement("SELECT * FROM Coffee WHERE id = ?", ps ->
        {
            ps.setInt(1,0);
            ResultSet set = ps.executeQuery();
            return set.getString("name");
        });

        System.out.println(name);

    }
}
