package util;

import dao.CoffeeDao;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {
	R apply(T t) throws E;

	static <T, R> Function<T, R> unchecked(ThrowingFunction<T, R, ? extends Exception> function) {
		return arg -> {
			try {
				return function.apply(arg);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		};
	}

	public static void main(String[] args) {
                ThrowingFunction.unchecked(f ->
                        new CoffeeDao().usePreparedStatement("Statement",ps -> {

                            return null;
                        })
                );
	}
}
