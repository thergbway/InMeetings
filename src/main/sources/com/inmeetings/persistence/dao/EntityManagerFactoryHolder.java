package com.inmeetings.persistence.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryHolder {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("inmeetings-main");

    private EntityManagerFactoryHolder() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
