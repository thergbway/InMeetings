package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.EntityManagerFactoryHolder;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

public class GenericDAOImpl<T extends Serializable> implements GenericDAO<T> {
    protected EntityManager entityManager = EntityManagerFactoryHolder.getEntityManagerFactory().createEntityManager();

    private Class<T> entityClass;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(entity);
        tx.commit();
    }

    @Override
    public T read(int key) {
        return entityManager.find(entityClass, key);
    }

    @Override
    public T update(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        T mergedEntity = entityManager.merge(entity);
        tx.commit();

        return mergedEntity;
    }

    @Override
    public void delete(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(entity);
        tx.commit();
    }
}
