package com.inmeetings.business.implementations;

import com.inmeetings.business.interfaces.ManagerService;
import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ManagerServiceImpl implements ManagerService {
    @EJB
    private ManagerDAO managerDAO;

    @Override
    public List<Manager> getManagersOfMeeting(Meeting meeting) {
        return managerDAO.getManagersOfMeeting(meeting);
    }
}
