package com.inmeetings.business.implementations;

import com.inmeetings.business.interfaces.ParticipantService;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ParticipantServiceImpl implements ParticipantService {
    @EJB
    private ParticipantDAO participantDAO;

    @Override
    public List<Participant> getParticipantsOfMeeting(Meeting meeting) {
        return participantDAO.getParticipantsOfMeeting(meeting);
    }

    @Override
    public void create(Participant participant) {
        participantDAO.create(participant);
    }

    @Override
    public void delete(Participant participant) {
        participantDAO.delete(participant);
    }

    @Override
    public Participant getByMeetingAndUser(Meeting m, User u) {
        return participantDAO.getByMeetingAndUser(m, u);
    }
}
