package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;

import java.util.List;

public interface MeetingDAO {
    List<Meeting> getAllMeetings();
}
