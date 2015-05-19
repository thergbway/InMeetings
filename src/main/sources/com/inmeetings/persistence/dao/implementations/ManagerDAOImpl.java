package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Stateful
public class ManagerDAOImpl implements GenericDAO<Manager>, ManagerDAO {
    @PersistenceContext(unitName = "inmeetings-main", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(ManagerDAOImpl.class.getName());
    private static final String GET_ALL_MANAGERS =
            "SELECT m FROM Manager m";
    private static final String GET_MEETINGS_USER_MANAGING =
            "SELECT m.meeting FROM Manager m WHERE m.user = :user";
    private static final String GET_MANAGERS_OF_MEETING =
            "SELECT m FROM Manager m WHERE m.meeting = :meeting";

    @Override
    public List<Manager> getAllManagers() {
        Query query = entityManager.createQuery(GET_ALL_MANAGERS);
        return query.getResultList();
    }

    @Override
    public List<Meeting> getMeetingsUserManaging(User u) {
        Query query = entityManager.createQuery(GET_MEETINGS_USER_MANAGING);
        query.setParameter("user", u);

        return query.getResultList();
    }

    @Override
    public List<Manager> getManagersOfMeeting(Meeting meeting) {
        Query query = entityManager.createQuery(GET_MANAGERS_OF_MEETING);
        query.setParameter("meeting", meeting);

        return query.getResultList();
    }

    @Override
    public void create(Manager manager) {
        entityManager.merge(manager);
    }

    @Override
    public Manager read(int key) {
        return entityManager.find(Manager.class, key);
    }

    @Override
    public Manager update(Manager manager) {
        return entityManager.merge(manager);
    }

    @Override
    public void delete(Manager manager) {
        Manager mergedManager = entityManager.merge(manager);
        entityManager.remove(mergedManager);
    }
}
