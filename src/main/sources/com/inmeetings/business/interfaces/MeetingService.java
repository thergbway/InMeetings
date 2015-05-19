package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface MeetingService {
    List<Meeting> getAllMeetingsForUser(User u);

    Meeting getById(int id);
}
