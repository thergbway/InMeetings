package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ParticipantDAOImpl implements GenericDAO<Participant>, ParticipantDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(ParticipantDAOImpl.class.getName());
    private static final String GET_ALL_PARTICIPANTS =
            "SELECT p FROM Participant p";

    @Override
    public List<Participant> getAllParticipants() {
        Query query = entityManager.createQuery(GET_ALL_PARTICIPANTS);
        return query.getResultList();
    }

    @Override
    public void create(Participant participant) {
        entityManager.persist(participant);
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
