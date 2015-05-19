package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;

import java.util.List;

public interface ManagerService {
    List<Manager> getManagersOfMeeting(Meeting meeting);

    void delete(Manager manager);

    Manager getByMeetingAndUser(Meeting m, User u);

    void save(Manager manager);
}
