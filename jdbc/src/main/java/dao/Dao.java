package dao;

import exception.SQLConnectionException;
import exception.SQLPreparedStatementException;
import util.ThrowingFunction;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface Dao<E, ID> {

	String DB_URL = "jdbc:postgresql://localhost:5432/coffee";
	String DB_USERNAME = "postgres";
	String DB_PASSWORD = "admin";

	default <T> T useConnection(ThrowingFunction<Connection, T, ? extends SQLException> function) throws SQLConnectionException {
		try (
			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			Transaction transaction = new Transaction(connection);
		) {
			T result = function.apply(connection);
			transaction.commit();
			return result;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new SQLConnectionException(exception);
		}
	}

	default <T> T usePreparedStatement(String sql, ThrowingFunction<PreparedStatement, T, ? extends SQLException> function) throws SQLConnectionException {
		return useConnection(connection -> {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				return function.apply(statement);
			} catch (Exception exception) {
				connection.rollback();
				exception.printStackTrace();
				throw new SQLPreparedStatementException(exception);
			}
		});
	}

	List<E> findAll();

	Optional<E> findById(ID id);

	void save(E e);

	void update(E e);

	void delete(E e);
}
