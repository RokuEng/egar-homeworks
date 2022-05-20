package exception;

import java.sql.SQLException;

public class SQLConnectionException extends SQLException {
	public SQLConnectionException(Throwable cause) {
		super(cause);
	}
}
