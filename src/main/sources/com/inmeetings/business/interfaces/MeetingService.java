package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;

import javax.swing.*;
import java.util.List;

public interface MeetingService {
    List<Meeting> getAllMeetingsForUser(User u);

    List<Meeting> getMeetingsUserManaging(User u);

    List<Meeting> getMeetingsUserParticipating(User u);

    List<Meeting> getMeetingsUserNotInvolved(User u);

    Meeting getById(int id);

    void save(Meeting meeting);
}
