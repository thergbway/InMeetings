package com.inmeetings.business.implementations;

import com.inmeetings.business.interfaces.UserService;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.UserDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {
    @EJB
    private UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public void save(User u) {
        userDAO.create(u);
    }

    @Override
    public User getById(int id) {
        return userDAO.read(id);
    }
}
