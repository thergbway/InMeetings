package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RoleDAOImpl implements RoleDAO, GenericDAO<Role> {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(RoleDAOImpl.class.getName());
    private static final String GET_USER_ROLE =
            "SELECT r FROM Role r WHERE r.roleName = 'user'";

    @Override
    public Role getUserRole() {
        Query query = entityManager.createQuery(GET_USER_ROLE);
        return ((Role) query.getSingleResult());
    }

    @Override
    public void create(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role read(int key) {
        return entityManager.find(Role.class, key);
    }

    @Override
    public Role update(Role role) {
        return entityManager.merge(role);
    }

    @Override
    public void delete(Role role) {
        Role mergedRole = entityManager.merge(role);
        entityManager.remove(mergedRole);
    }
}