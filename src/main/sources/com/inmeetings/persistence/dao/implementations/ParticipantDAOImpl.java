package com.inmeetings.persistence.dao.implementations;

import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ParticipantDAOImpl extends GenericDAOImpl<Participant> implements ParticipantDAO {
    private static final Logger LOG = Logger.getLogger(ParticipantDAOImpl.class.getName());
    private static final String GET_ALL_PARTICIPANTS =
            "SELECT p FROM Participant p";

    public ParticipantDAOImpl() {
        super(Participant.class);
    }

    @Override
    public List<Participant> getAllParticipants() {
        Query query = entityManager.createQuery(GET_ALL_PARTICIPANTS);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        List<Participant> participants = query.getResultList();
        tx.commit();

        return participants;
    }
}
