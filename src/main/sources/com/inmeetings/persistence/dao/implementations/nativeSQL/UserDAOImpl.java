package com.inmeetings.persistence.dao.implementations.nativeSQL;

import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.UserDAO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Stateless(name = "UserDAOWithNativeSQL")
public class UserDAOImpl implements UserDAO, GenericDAO<User> {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;
    @EJB
    private EntityUtils entityUtils;

    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public List<User> getAllUsers() {
        String sql1 = "SELECT id, login, password, role_id, first_name, last_name FROM user_total";
        Query q1 = entityManager.createNativeQuery(sql1);
        List<Object[]> usersListAsObjects = q1.getResultList();

        LinkedList<User> users = new LinkedList<>();
        usersListAsObjects.forEach(objects -> users.add(entityUtils.constructUser(objects)));

        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        String sql1 = "SELECT id, login, password, role_id, first_name, last_name FROM user_total WHERE login= :login";
        Query q1 = entityManager.createNativeQuery(sql1);
        q1.setParameter("login", login);
        Object[] userResult = ((Object[]) q1.getSingleResult());

        return entityUtils.constructUser(userResult);
    }

    @Override
    public void create(User entity){
        String sql1 = "INSERT INTO role(role_name) SELECT :role_name " +
                "WHERE NOT EXISTS (SELECT role_name FROM role WHERE role_name = :role_name)";
        Query q1 = entityManager.createNativeQuery(sql1);
        q1.setParameter("role_name", entity.getRole().getRoleName());
        q1.executeUpdate();

        String sql2 = "SELECT id FROM role WHERE role_name= :role_name";
        Query q2 = entityManager.createNativeQuery(sql2);
        q2.setParameter("role_name", entity.getRole().getRoleName());
        int role_id = (int) q2.getSingleResult();

        String sql3 = "INSERT INTO user_total(login, password, role_id, first_name, last_name) " +
                "VALUES (:login, :password, :role_id, :first_name, :last_name)";
        Query q3 = entityManager.createNativeQuery(sql3);
        q3.setParameter("login", entity.getLogin());
        q3.setParameter("password", entity.getPassword());
        q3.setParameter("role_id", role_id);
        q3.setParameter("first_name", entity.getFirstName());
        q3.setParameter("last_name", entity.getLastName());
        q3.executeUpdate();
    }

    @Override
    public User read(int key) {
        String sql = "SELECT id, login, password, role_id, first_name, last_name FROM user_total WHERE id=:id";
        Query q1 = entityManager.createNativeQuery(sql);
        q1.setParameter("id", key);

        return entityUtils.constructUser(((Object[]) q1.getSingleResult()));
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
