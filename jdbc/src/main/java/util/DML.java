package util;

public enum DML {
	SELECT("SELECT"),
	INSERT("INSERT"),
	UPDATE("UPDATE"),
	DELETE("DELETE"),
	ALL("*"),
	FROM("FROM"),
	WHERE("WHERE"),
	JOIN("JOIN"),
	LEFT_JOIN("LEFT JOIN"),
	ON("ON"),
	SET("SET"),
	INTO("INTO"),
	VALUES_START("VALUES("),
	VALUES_END(")");

	public static String PREPARED(String s) {
		return s + " = ?";
	}

	public static String PREPARED_NEXT(String s) {
		return s + " = ?,";
	}

	final String s;

	DML(String s) {
		this.s = s;
	}
}
