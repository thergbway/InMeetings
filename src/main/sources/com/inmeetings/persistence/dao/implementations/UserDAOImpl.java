package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.UserDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String GET_ALL_USERS =
            "SELECT u FROM User u";

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery(GET_ALL_USERS);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        List<User> users = query.getResultList();
        tx.commit();

        return users;
    }
}
