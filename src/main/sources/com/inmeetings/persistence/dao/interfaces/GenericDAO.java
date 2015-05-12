package com.inmeetings.persistence.dao.interfaces;

import java.io.Serializable;

public interface GenericDAO<T extends Serializable> {
    void create(T entity);

    T read(int key);

    T update(T entity);

    void delete(T entity);
}
