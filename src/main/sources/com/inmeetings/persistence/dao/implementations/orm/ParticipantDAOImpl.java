package com.inmeetings.persistence.dao.implementations.orm;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ParticipantDAOImpl implements GenericDAO<Participant>, ParticipantDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(ParticipantDAOImpl.class.getName());
    private static final String GET_ALL_PARTICIPANTS =
            "SELECT p FROM Participant p";
    private static final String GET_MEETINGS_USER_PARTICIPATING =
            "SELECT p.meeting FROM Participant p WHERE p.user = :user";
    private static final String GET_PARTICIPANTS_OF_MEETING =
            "SELECT p FROM Participant p WHERE p.meeting = :meeting";
    private static final String GET_PARTICIPANT_BY_MEETING_AND_USER =
            "SELECT p FROM Participant p WHERE p.user = :user AND p.meeting = :meeting";

    @Override
    public List<Participant> getAllParticipants() {
        Query query = entityManager.createQuery(GET_ALL_PARTICIPANTS);
        return query.getResultList();
    }

    @Override
    public List<Meeting> getMeetingsUserParticipating(User u) {
        Query query = entityManager.createQuery(GET_MEETINGS_USER_PARTICIPATING);
        query.setParameter("user", u);

        return query.getResultList();
    }

    @Override
    public List<Participant> getParticipantsOfMeeting(Meeting meeting) {
        Query query = entityManager.createQuery(GET_PARTICIPANTS_OF_MEETING);
        query.setParameter("meeting", meeting);

        return query.getResultList();
    }

    @Override
    public Participant getByMeetingAndUser(Meeting m, User u) {
        Query query = entityManager.createQuery(GET_PARTICIPANT_BY_MEETING_AND_USER);
        query.setParameter("user", u);
        query.setParameter("meeting", m);

        Participant participant = null;
        try {
            participant = (Participant) query.getSingleResult();
        }catch (NoResultException e){
            participant = null;
        }

        return participant;
    }

    @Override
    public void create(Participant participant) {
        entityManager.merge(participant);
    }

    @Override
    public Participant read(int key) {
        return entityManager.find(Participant.class, key);
    }

    @Override
    public Participant update(Participant participant) {
        return entityManager.merge(participant);
    }

    @Override
    public void delete(Participant participant) {
        Participant mergedParticipant = entityManager.merge(participant);
        entityManager.remove(mergedParticipant);
    }
}
