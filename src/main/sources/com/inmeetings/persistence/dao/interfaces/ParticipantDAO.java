package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface ParticipantDAO extends GenericDAO<Participant> {
    List<Participant> getAllParticipants();

    List<Meeting> getMeetingsUserParticipating(User u);

    List<Participant> getParticipantsOfMeeting(Meeting meeting);

    Participant getByMeetingAndUser(Meeting m, User u);
}
