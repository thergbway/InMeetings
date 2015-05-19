package com.inmeetings.business.implementations;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.business.interfaces.ParticipantService;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.PriorityQueue;

@Stateless
public class ParticipantServiceImpl implements ParticipantService {
    @EJB
    private ParticipantDAO participantDAO;

    @Override
    public List<Participant> getParticipantsOfMeeting(Meeting meeting) {
        return participantDAO.getParticipantsOfMeeting(meeting);
    }
}
