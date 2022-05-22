package util;

import lombok.AllArgsConstructor;

public class InnerQuery {
	public final Object[] args;

	public InnerQuery(Object... args) {
		this.args = args;
	}
}
