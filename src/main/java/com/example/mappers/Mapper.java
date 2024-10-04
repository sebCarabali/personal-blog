package com.example.mappers;

public interface Mapper<T> {

    String mapToJson(T entity);
    T mapToEntity(String jsonContent);
}
