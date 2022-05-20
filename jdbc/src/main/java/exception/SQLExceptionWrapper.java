package exception;

public class SQLExceptionWrapper extends RuntimeException {
	public SQLExceptionWrapper(Throwable cause) {
		super(cause);
	}
}
