package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;
import org.apache.log4j.Logger;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Stateful
public class ParticipantDAOImpl implements GenericDAO<Participant>, ParticipantDAO {
    @PersistenceContext(unitName = "inmeetings-main", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(ParticipantDAOImpl.class.getName());
    private static final String GET_ALL_PARTICIPANTS =
            "SELECT p FROM Participant p";
    private static final String GET_MEETINGS_USER_PARTICIPATING =
            "SELECT p.meeting FROM Participant p WHERE p.user = :user";
    private static final String GET_PARTICIPANTS_OF_MEETING =
            "SELECT p FROM Participant p WHERE p.meeting = :meeting";

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
