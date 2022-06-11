package com.RokuEng.springdata.util;

@FunctionalInterface
public interface TriFunction<T, U, V,R> {

    R apply(T t, U u, V v);

}
