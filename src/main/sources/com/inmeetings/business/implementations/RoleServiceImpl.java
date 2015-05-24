package com.inmeetings.business.implementations;

import com.inmeetings.business.interfaces.RoleService;
import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RoleServiceImpl implements RoleService {
    @EJB(beanName = "RoleDAOWithNativeSQL")
    private RoleDAO roleDAO;

    @Override
    public Role getUserRole() {
        return roleDAO.getUserRole();
    }
}
