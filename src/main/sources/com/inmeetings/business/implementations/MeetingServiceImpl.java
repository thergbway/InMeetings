package com.inmeetings.business.implementations;

import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.ManagerDAO;
import com.inmeetings.persistence.dao.interfaces.MeetingDAO;
import com.inmeetings.persistence.dao.interfaces.ParticipantDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class MeetingServiceImpl implements MeetingService {
    @EJB
    private ParticipantDAO participantDAO;
    @EJB(beanName = "ManagerDAOWithNativeSQL")
    private ManagerDAO managerDAO;
    @EJB(beanName = "MeetingDAOWithNativeSQL")
    private MeetingDAO meetingDAO;

    @Override
    public List<Meeting> getAllMeetingsForUser(User u) {
        List<Meeting> meetings = new LinkedList<>();
        meetings.addAll(participantDAO.getMeetingsUserParticipating(u));
        meetings.addAll(managerDAO.getMeetingsUserManaging(u));

        return meetings;
    }

    @Override
    public Meeting getById(int id) {
        return meetingDAO.read(id);
    }

    @Override
    public void save(Meeting meeting) {
        meetingDAO.create(meeting);
    }

    @Override
    public List<Meeting> getMeetingsUserManaging(User u) {
        return managerDAO.getMeetingsUserManaging(u);
    }

    @Override
    public List<Meeting> getMeetingsUserParticipating(User u) {
        return participantDAO.getMeetingsUserParticipating(u);
    }

    @Override
    public List<Meeting> getMeetingsUserNotInvolved(User u) {
        return meetingDAO.getMeetingsUserNotInvolved(u);
    }
}
