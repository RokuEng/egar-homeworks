package dao;

import exception.SQLConnectionException;
import exception.SQLExceptionWrapper;
import exception.SQLPreparedStatementException;
import util.ThrowingFunction;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface Dao<ENTITY, ID> {

    String DB_URL = "jdbc:postgresql://localhost:5432/coffee";
    String DB_USERNAME = "postgres";
    String DB_PASSWORD = "admin";

    default <T> T useConnection(ThrowingFunction<Connection, T, ? extends SQLException> function) throws SQLConnectionException {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            return function.apply(connection);
        } catch (Exception exception) {
            throw new SQLConnectionException(exception);
        }
    }

    default <T> T usePreparedStatement(String query, ThrowingFunction<PreparedStatement, T, ? extends SQLException> function) throws SQLConnectionException {
        return useConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                return function.apply(statement);
            } catch (Exception exception) {
                throw new SQLPreparedStatementException(exception);
            }
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
