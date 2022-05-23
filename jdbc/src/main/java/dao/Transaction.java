package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction implements AutoCloseable {
	private Connection connection;

	private boolean committed = false;

	public Transaction(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		this.connection = connection;
	}

	@Override
	public void close() throws Exception {
		if (!committed) {
			connection.rollback();
		}
	}

	public void commit() throws SQLException {
		connection.commit();
		committed = true;
	}
}
