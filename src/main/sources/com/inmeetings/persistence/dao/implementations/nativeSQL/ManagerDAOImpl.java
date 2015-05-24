package com.inmeetings.persistence.dao.implementations.nativeSQL;


import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Stateless(name = "ManagerDAOWithNativeSQL")
public class ManagerDAOImpl implements GenericDAO<Manager>, ManagerDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;
    @EJB
    private EntityUtils entityUtils;

    private static final Logger LOG = Logger.getLogger(ManagerDAOImpl.class.getName());

    @Override
    public List<Manager> getAllManagers() {
        String sql1 = "select id, meeting_id, user_id, description from manager";
        Query q1 = entityManager.createNativeQuery(sql1);
        List<Object[]> managersObjectParams = q1.getResultList();
        List<Manager> managers = new LinkedList<>();

        managersObjectParams.forEach(objects -> managers.add(entityUtils.constructManager(objects)));

        return managers;
    }

    @Override
    public List<Meeting> getMeetingsUserManaging(User u) {
        String sql = "select id, meeting_id, user_id, description from manager where user_id = :user_id";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("user_id", u.getId());

        List<Object[]> managersObjectParams = q.getResultList();
        List<Manager> managers = new LinkedList<>();

        managersObjectParams.forEach(objects -> managers.add(entityUtils.constructManager(objects)));

        List<Meeting> meetings = new LinkedList<>();
        managers.forEach(manager -> meetings.add(manager.getMeeting()));

        return meetings;
    }

    @Override
    public List<Manager> getManagersOfMeeting(Meeting meeting) {
        String sql = "select id, meeting_id, user_id, description from manager where meeting_id = :meeting_id";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("meeting_id", meeting.getId());

        List<Object[]> managersObjectParams = q.getResultList();
        List<Manager> managers = new LinkedList<>();

        managersObjectParams.forEach(objects -> managers.add(entityUtils.constructManager(objects)));

        return managers;
    }

    @Override
    public Manager getByMeetingAndUser(Meeting m, User u) {
        String sql = "select id, meeting_id, user_id, description from manager where meeting_id = :meeting_id and" +
                " user_id = :user_id";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("meeting_id", m.getId());
        q.setParameter("user_id", u.getId());

        Manager manager = null;
        try{
            Object[] managerParams = (Object[]) q.getSingleResult();
            manager = entityUtils.constructManager(managerParams);
        }catch (Exception e){
            manager = null;
        }

        return manager;
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