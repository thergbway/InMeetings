package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.UserDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAOImpl implements UserDAO, GenericDAO<User> {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String GET_ALL_USERS =
            "SELECT u FROM User u";
    private static final String GET_USER_BY_LOGIN =
            "SELECT u FROM User u WHERE u.login = :login";

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery(GET_ALL_USERS);
        return query.getResultList();
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = entityManager.createQuery(GET_USER_BY_LOGIN);
        query.setParameter("login", login);
        return ((User) query.getSingleResult());
    }

    @Override
    public void create(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public User read(int key) {
        return entityManager.find(User.class, key);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        User mergedUser = entityManager.merge(user);
        entityManager.remove(mergedUser);
    }
}
