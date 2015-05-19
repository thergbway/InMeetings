package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.MeetingDAO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class MeetingDAOImpl implements GenericDAO<Meeting>, MeetingDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(MeetingDAOImpl.class.getName());
    private static final String GET_ALL_MEETINGS =
            "SELECT m FROM Meeting m";
    private static final String GET_MEETING_USER_NOT_INVOLVED_AS_PARTICIPANT =
            "SELECT m FROM Meeting m WHERE m NOT IN " +
                    "(SELECT p.meeting FROM Participant p WHERE p.user = :user)";
    private static final String GET_MEETING_USER_NOT_INVOLVED_AS_MANAGER =
            "SELECT m FROM Meeting m WHERE m NOT IN " +
                    "(SELECT m.meeting FROM Manager m WHERE m.user= :user)";

    @Override
    public List<Meeting> getAllMeetings() {
        Query query = entityManager.createQuery(GET_ALL_MEETINGS);
        return query.getResultList();
    }

    @Override
    public List<Meeting> getMeetingsUserNotInvolved(User u) {
        Query q1 = entityManager.createQuery(GET_MEETING_USER_NOT_INVOLVED_AS_PARTICIPANT);
        q1.setParameter("user", u);
        List<Meeting> meetingsUserNotParticipant= q1.getResultList();

        Query q2 = entityManager.createQuery(GET_MEETING_USER_NOT_INVOLVED_AS_MANAGER);
        q2.setParameter("user", u);
        List<Meeting> meetingsUserNotManager= q2.getResultList();

        LinkedList<Meeting> resultList = new LinkedList<>(meetingsUserNotParticipant);
        resultList.retainAll(meetingsUserNotManager);

        return resultList;
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
