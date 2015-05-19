package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.ManagerService;
import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.business.interfaces.ParticipantService;
import com.inmeetings.business.interfaces.UserService;
import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Participant;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.presentation.utils.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MeetingLeaveServlet", urlPatterns = "meetingLeave/*")
public class MeetingLeaveServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;
    @EJB
    private ParticipantService participantService;
    @EJB
    private ManagerService managerService;
    @EJB
    private MeetingService meetingService;
    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }

        Integer meetingId = null;
        Integer userId = null;
        try {
            String meetingIdAsStr = request.getPathInfo().split("meetingId=")[1].split("/userId=")[0];
            String userIdAsStr = request.getPathInfo().split("userId=")[1];
            meetingId = Integer.parseInt(meetingIdAsStr);
            userId = Integer.parseInt(userIdAsStr);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Meeting meeting = meetingService.getById(meetingId);
        User user = userService.getById(userId);
        if(meeting == null || user == null){
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }
        Participant participant = participantService.getByMeetingAndUser(meeting, user);
        if(participant != null){
            participantService.delete(participant);
            response.sendRedirect(request.getContextPath() + "/meetingsAll");
            return;
        }
        else {
            Manager manager = managerService.getByMeetingAndUser(meeting, user);
            if(managerService != null) {
                managerService.delete(manager);
                response.sendRedirect(request.getContextPath() + "/meetingsAll");
                return;
            }
            else{
                response.sendRedirect(request.getContextPath() + "/404.html");
                return;
            }
        }
    }
}
