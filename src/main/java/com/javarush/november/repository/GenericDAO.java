package com.javarush.november.repository;

import java.util.List;

public interface GenericDAO<T> {
    T getById(int id);

    List<T> getItems(int offset, int count);

    List<T> getAll();

    int getTotalCount();

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(final int entityId);
}
