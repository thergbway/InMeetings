package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
}