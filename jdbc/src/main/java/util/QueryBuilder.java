package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static util.DML.*;

public class QueryBuilder {

	public static String build(Object... args) {
		StringBuilder sb = new StringBuilder();

		List<String> keywords = Arrays.stream(args)
			.filter(o -> {
				return o.getClass() == String.class || o.getClass() == DML.class;
			})
			.map(kw -> {
				return kw instanceof String ? ((String) kw) : ((DML) kw).s;
			})
			.collect(Collectors.toList());

		keywords.stream().forEachOrdered(s -> sb.append(s + " "));

		return sb.toString();
	};
}
