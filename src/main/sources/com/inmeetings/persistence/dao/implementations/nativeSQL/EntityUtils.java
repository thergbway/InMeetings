package com.inmeetings.persistence.dao.implementations.nativeSQL;

import com.inmeetings.persistence.dao.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;

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

    public Meeting constructMeeting(Object[] params) {
        int id = (int) params[0];
        String name = (String) params[1];
        Timestamp start = ((Timestamp) params[2]);
        Timestamp end = (Timestamp) params[3];
        String description = (String) params[4];

        Meeting meeting = new Meeting();
        meeting.setId(id);
        meeting.setName(name);
        meeting.setStartTime(start);
        meeting.setEndTime(end);
        meeting.setDescription(description);

        return meeting;
    }

    public Manager constructManager(Object[] params) {
        int id = (int) params[0];
        int meetingId = (int) params[1];
        int userId = (int) params[2];
        String description = (String) params[3];

        String sql1 = "select id, name, start_time, end_time, description from meeting where id=:meeting_id";
        Query q1 = entityManager.createNativeQuery(sql1);
        q1.setParameter("meeting_id", meetingId);
        Meeting meeting = constructMeeting(((Object[]) q1.getSingleResult()));

        String sql2 = "select id, login, password, role_id, first_name, last_name from user_total where id = :user_id";
        Query q2 = entityManager.createNativeQuery(sql2);
        q2.setParameter("user_id", userId);
        User user = constructUser(((Object[]) q2.getSingleResult()));

        Manager manager = new Manager();
        manager.setId(id);
        manager.setDescription(description);
        manager.setMeeting(meeting);
        manager.setUser(user);

        return manager;
    }

    public Participant constructParticipant(Object[] params) {
        int id = (int) params[0];
        int meetingId = (int) params[1];
        int userId = (int) params[2];

        String sql1 = "select id, name, start_time, end_time, description from meeting where id=:meeting_id";
        Query q1 = entityManager.createNativeQuery(sql1);
        q1.setParameter("meeting_id", meetingId);
        Meeting meeting = constructMeeting(((Object[]) q1.getSingleResult()));

        String sql2 = "select id, login, password, role_id, first_name, last_name from user_total where id = :user_id";
        Query q2 = entityManager.createNativeQuery(sql2);
        q2.setParameter("user_id", userId);
        User user = constructUser(((Object[]) q2.getSingleResult()));

        Participant participant = new Participant();
        participant.setId(id);
        participant.setMeeting(meeting);
        participant.setUser(user);

        return participant;
    }
}
