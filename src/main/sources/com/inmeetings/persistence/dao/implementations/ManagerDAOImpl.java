package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ManagerDAOImpl extends GenericDAOImpl<Manager> implements ManagerDAO {
    private static final Logger LOG = Logger.getLogger(ManagerDAOImpl.class.getName());
    private static final String GET_ALL_MANAGERS =
            "SELECT m FROM Manager m";

    public ManagerDAOImpl() {
        super(Manager.class);
    }

    @Override
    public List<Manager> getAllManagers() {
        Query query = entityManager.createQuery(GET_ALL_MANAGERS);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        List<Manager> managers = query.getResultList();
        tx.commit();

        return managers;
    }
}
