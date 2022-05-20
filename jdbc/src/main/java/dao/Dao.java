package dao;

import exception.SQLConnectionException;
import exception.SQLPreparedStatementException;
import util.ThrowingFunction;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface Dao<ENTITY, ID> {

    String DB_URL = null;
    String DB_USERNAME = null;
    String DB_PASSWORD = null;

    default <T> T useConnection(ThrowingFunction<Connection, T, SQLException> function) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            return function.apply(connection);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    default <T> T usePreparedStatement(String query, ThrowingFunction<PreparedStatement, T, SQLException> function) {
        return useConnection(connection -> {
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                return function.apply(statement);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    default <T> T useResultSet(String query, ThrowingFunction<PreparedStatement, T, SQLException> function) {
        return usePreparedStatement(query, ps -> {
            ResultSet rs = ps.executeQuery();
            return null;
        });
    }

//    List<ENTITY> findAll();
//
//    Optional<ENTITY> findById(ID id);
//
//    void save(ENTITY entity);
//
//    void update(ENTITY entity);
//
//    void delete(ENTITY entity);
}
