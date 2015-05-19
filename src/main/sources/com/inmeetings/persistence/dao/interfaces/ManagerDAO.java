package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface ManagerDAO extends GenericDAO<Manager> {
    List<Manager> getAllManagers();

    List<Meeting> getMeetingsUserManaging(User u);

    List<Manager> getManagersOfMeeting(Meeting meeting);
}
