package com.inmeetings.persistence.dao.implementations.nativeSQL;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "RoleDAOWithNativeSQL")
public class RoleDAOImpl implements RoleDAO, GenericDAO<Role> {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(RoleDAOImpl.class.getName());

    @Override
    public Role getUserRole() {
        String sql = "select * from role where role_name='user'";
        Query q1 = entityManager.createNativeQuery(sql, Role.class);
        return (Role) q1.getSingleResult();
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