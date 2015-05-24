package com.inmeetings.persistence.dao.implementations.nativeSQL;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.MeetingDAO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Stateless(name = "MeetingDAOWithNativeSQL")
public class MeetingDAOImpl implements GenericDAO<Meeting>, MeetingDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;
    @EJB
    private EntityUtils entityUtils;

    private static final Logger LOG = Logger.getLogger(MeetingDAOImpl.class.getName());

    @Override
    public List<Meeting> getAllMeetings() {
        String sql = "select id, name, start_time, end_time, description from meeting";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> meetingsParamsList = query.getResultList();
        List<Meeting> meetings = new LinkedList<>();

        meetingsParamsList.forEach(objects -> meetings.add(entityUtils.constructMeeting(objects)));

        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsUserNotInvolved(User u) {
        String sql = "select id, name, start_time, end_time, description from meeting " +
                "where id not in " +
                "(select meeting_id from participant where user_id = :user_id) " +
                "and id not in " +
                "(select meeting_id from manager where user_id = :user_id)";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("user_id", u.getId());
        List<Object[]> meetingsObjectParams = q.getResultList();
        List<Meeting> meetings = new LinkedList<>();

        meetingsObjectParams.forEach(objects -> meetings.add(entityUtils.constructMeeting(objects)));

        return meetings;
    }

    @Override
    public void create(Meeting meeting) {
        entityManager.merge(meeting);
    }

    @Override
    public Meeting read(int key) {
        return entityManager.find(Meeting.class, key);
    }

    @Override
    public Meeting update(Meeting meeting) {
        return entityManager.merge(meeting);
    }

    @Override
    public void delete(Meeting meeting) {
        Meeting mergedMeeting = entityManager.merge(meeting);
        entityManager.remove(mergedMeeting);
    }
}
