package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getParticipantsOfMeeting(Meeting meeting);
}
