package com.inmeetings.business.implementations;

import com.inmeetings.business.interfaces.ManagerService;
import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ManagerServiceImpl implements ManagerService {
    @EJB(beanName = "ManagerDAOWithNativeSQL")
    private ManagerDAO managerDAO;

    @Override
    public List<Manager> getManagersOfMeeting(Meeting meeting) {
        return managerDAO.getManagersOfMeeting(meeting);
    }

    @Override
    public void delete(Manager manager) {
        managerDAO.delete(manager);
    }

    @Override
    public void save(Manager manager) {
        managerDAO.create(manager);
    }

    @Override
    public Manager getByMeetingAndUser(Meeting m, User u) {
        return managerDAO.getByMeetingAndUser(m, u);
    }
}
