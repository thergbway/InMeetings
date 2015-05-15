package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {
    List<User> getAllUsers();
}
