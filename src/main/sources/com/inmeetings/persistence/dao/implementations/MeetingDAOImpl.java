package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.MeetingDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class MeetingDAOImpl implements GenericDAO<Meeting>, MeetingDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(MeetingDAOImpl.class.getName());
    private static final String GET_ALL_MEETINGS =
            "SELECT m FROM Meeting m";

    @Override
    public List<Meeting> getAllMeetings() {
        Query query = entityManager.createQuery(GET_ALL_MEETINGS);
        return query.getResultList();
    }

    @Override
    public void create(Meeting meeting) {
        entityManager.persist(meeting);
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
