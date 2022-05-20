package exception;

import java.sql.SQLException;

public class SQLPreparedStatementException extends SQLException {
	public SQLPreparedStatementException(Throwable cause) {
		super(cause);
	}
}
