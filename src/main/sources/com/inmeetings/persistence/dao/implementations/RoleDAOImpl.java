package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RoleDAOImpl implements RoleDAO, GenericDAO<Role> {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(RoleDAOImpl.class.getName());
    private static final String GET_ALL_ROLES =
            "SELECT r FROM Role r";

    @Override
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery(GET_ALL_ROLES);
        return query.getResultList();
    }

    @Override
    public void create(Role entity) {
        entityManager.persist(entity);
    }

    @Override
    public Role read(int key) {
        return entityManager.find(Role.class, key);
    }

    @Override
    public Role update(Role entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Role entity) {
        Role mergedRole = entityManager.merge(entity);
        entityManager.remove(mergedRole);
    }
}
