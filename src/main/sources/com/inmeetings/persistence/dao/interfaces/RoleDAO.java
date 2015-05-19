package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Role;

public interface RoleDAO extends GenericDAO<Role> {
    Role getUserRole();
}
