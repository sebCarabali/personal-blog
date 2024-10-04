package com.example.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> extends Repository<T> {

    T save(T entity);
    List<T> findAll();
    List<T> findBy(Matcher<T> matcher);
    Optional<T> findOneBy(Matcher<T> matcher);
    T update(T entity);
    boolean delete(Matcher<T> matcher);
}
