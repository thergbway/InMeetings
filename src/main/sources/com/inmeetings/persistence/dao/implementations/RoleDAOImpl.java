package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {
    private static final Logger LOG = Logger.getLogger(RoleDAOImpl.class.getName());
    private static final String GET_ALL_ROLES =
            "SELECT r FROM Role r";

    public RoleDAOImpl() {
        super(Role.class);
    }

    @Override
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery(GET_ALL_ROLES);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        List<Role> roles = query.getResultList();
        tx.commit();

        return roles;
    }
}
