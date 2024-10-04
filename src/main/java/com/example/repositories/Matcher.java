package com.example.repositories;

@FunctionalInterface
public interface Matcher<T> {

    boolean matches(T entity);
}
