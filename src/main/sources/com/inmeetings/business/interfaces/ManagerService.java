package com.inmeetings.business.interfaces;

import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;

import java.util.List;

public interface ManagerService {
    public List<Manager> getManagersOfMeeting(Meeting meeting);
}
