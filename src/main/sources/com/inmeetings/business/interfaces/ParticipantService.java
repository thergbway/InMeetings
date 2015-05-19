package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface ParticipantService {
    List<Participant> getParticipantsOfMeeting(Meeting meeting);

    void create(Participant participant);

    void delete(Participant participant);

    Participant getByMeetingAndUser(Meeting m, User u);
}