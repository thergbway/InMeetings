package com.inmeetings.persistence.dao.implementations.nativeSQL;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EntityUtils {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    public EntityUtils() {
    }

    public User constructUser(Object[] params) {
        int userId = (int) params[0];
        String userLogin = (String) params[1];
        String userPassword = (String) params[2];
        int roleId = (int) params[3];
        String userFirstName = (String) params[4];
        String userLastName = (String) params[5];

        String sql2 = "SELECT id, role_name FROM role WHERE role.id = :role_id";
        Query q2 = entityManager.createNativeQuery(sql2);
        q2.setParameter("role_id", roleId);
        Object[] roleResult = (Object[]) q2.getSingleResult();
        String roleName = (String) roleResult[1];

        Role role = new Role();
        role.setRoleName(roleName);
        role.setId((roleId));

        User user = new User();
        user.setId(userId);
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        user.setRole(role);
        user.setFirstName(userFirstName);
        user.setLastName(userLastName);

        return user;
    }
}
