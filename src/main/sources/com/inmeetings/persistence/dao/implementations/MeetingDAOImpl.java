package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.interfaces.MeetingDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class MeetingDAOImpl extends GenericDAOImpl<Meeting> implements MeetingDAO {
    private static final Logger LOG = Logger.getLogger(MeetingDAOImpl.class.getName());
    private static final String GET_ALL_MEETINGS =
            "SELECT m FROM Meeting m";

    public MeetingDAOImpl() {
        super(Meeting.class);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        Query query = entityManager.createQuery(GET_ALL_MEETINGS);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        List<Meeting> meetings = query.getResultList();
        tx.commit();

        return meetings;
    }
}
