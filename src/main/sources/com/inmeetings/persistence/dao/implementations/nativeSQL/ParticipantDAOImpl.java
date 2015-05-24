package com.inmeetings.persistence.dao.implementations.nativeSQL;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.GenericDAO;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Stateless(name = "ParticipantDAOWithNativeSQL")
public class ParticipantDAOImpl implements GenericDAO<Participant>, ParticipantDAO {
    @PersistenceContext(unitName = "inmeetings-main")
    private EntityManager entityManager;
    @EJB
    private EntityUtils entityUtils;

    private static final Logger LOG = Logger.getLogger(ParticipantDAOImpl.class.getName());

    @Override
    public List<Participant> getAllParticipants() {
        String sql1 = "select id, meeting_id, user_id from participant";
        Query q1 = entityManager.createNativeQuery(sql1);
        List<Object[]> participantsObjectParams = q1.getResultList();
        List<Participant> participants = new LinkedList<>();

        participantsObjectParams.forEach(objects -> participants.add(entityUtils.constructParticipant(objects)));

        return participants;
    }

    @Override
    public List<Meeting> getMeetingsUserParticipating(User u) {
        String sql = "select id, meeting_id, user_id from participant where user_id = :user_id";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("user_id", u.getId());

        List<Object[]> participantObjectParams = q.getResultList();
        List<Participant> participants = new LinkedList<>();

        participantObjectParams.forEach(objects -> participants.add(entityUtils.constructParticipant(objects)));

        List<Meeting> meetings = new LinkedList<>();
        participants.forEach(participant -> meetings.add(participant.getMeeting()));

        return meetings;
    }

    @Override
    public List<Participant> getParticipantsOfMeeting(Meeting meeting) {
        String sql = "select id, meeting_id, user_id from participant where meeting_id = :meeting_id";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("meeting_id", meeting.getId());

        List<Object[]> participantsObjectParams = q.getResultList();
        List<Participant> participants = new LinkedList<>();

        participantsObjectParams.forEach(objects -> participants.add(entityUtils.constructParticipant(objects)));

        return participants;
    }

    @Override
    public Participant getByMeetingAndUser(Meeting m, User u) {
        String sql = "select id, meeting_id, user_id from participant where meeting_id = :meeting_id and" +
                " user_id = :user_id";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("meeting_id", m.getId());
        q.setParameter("user_id", u.getId());

        Participant participant = null;
        try {
            Object[] participantsParams = (Object[]) q.getSingleResult();
            participant = entityUtils.constructParticipant(participantsParams);
        } catch (Exception e) {
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
