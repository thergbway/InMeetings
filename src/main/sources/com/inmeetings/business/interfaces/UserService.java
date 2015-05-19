package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getByLogin(String login);

    void save(User u);

    User getById(int id);
}
