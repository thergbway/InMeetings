package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ManagerDAOImpl implements GenericDAO<Manager>, ManagerDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(ManagerDAOImpl.class.getName());
    private static final String GET_ALL_MANAGERS =
            "SELECT m FROM Manager m";

    @Override
    public List<Manager> getAllManagers() {
        Query query = entityManager.createQuery(GET_ALL_MANAGERS);
        return query.getResultList();
    }

    @Override
    public void create(Manager manager) {
        entityManager.persist(manager);
    }

    @Override
    public Manager read(int key) {
        return entityManager.find(Manager.class, key);
    }

    @Override
    public Manager update(Manager manager) {
        return entityManager.merge(manager);
    }

    @Override
    public void delete(Manager manager) {
        Manager mergedManager = entityManager.merge(manager);
        entityManager.remove(mergedManager);
    }
}
