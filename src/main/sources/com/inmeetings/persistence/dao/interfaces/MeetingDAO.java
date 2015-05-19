package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface MeetingDAO extends GenericDAO<Meeting> {
    List<Meeting> getAllMeetings();
}
