package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByLogin(String login);

    void create(User u);
}
