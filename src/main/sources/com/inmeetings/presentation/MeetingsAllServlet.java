package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.presentation.utils.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@WebServlet(name = "MeetingsAllServlet", urlPatterns = "meetingsAll")
public class MeetingsAllServlet extends HttpServlet {
    @EJB
    private MeetingService meetingService;
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect("index");
            return;
        }
        response.setCharacterEncoding("UTF-8");

        User loggedUser = authUtils.getLoggedUser(request);
        List<Meeting> meetingsUserManaging = meetingService.getMeetingsUserManaging(loggedUser);
        List<Meeting> meetingsUserParticipating = meetingService.getMeetingsUserParticipating(loggedUser);

        setUserManagingMeetingsAttributes(request, loggedUser, meetingsUserManaging);
        setUserParticipatingMeetingsAttributes(request, loggedUser, meetingsUserParticipating);
        setLoggedUserAttributes(request, loggedUser);

        getServletContext().getRequestDispatcher("/meetingsAll.jsp").forward(request, response);
    }

    private void setLoggedUserAttributes(HttpServletRequest request, User loggedUser) {
        request.setAttribute("logged_user_first_name", loggedUser.getFirstName());
        request.setAttribute("logged_user_last_name", loggedUser.getLastName());
        request.setAttribute("logged_user_id", loggedUser.getId());
    }

    private void setUserParticipatingMeetingsAttributes(HttpServletRequest request, User loggedUser, List<Meeting> meetingsUserParticipating) {
        List<String> meetingsUserParticipatingNames = new LinkedList<>();
        List<String> meetingsUserParticipatingAboutURLs = new LinkedList<>();
        List<String> meetingsUserParticipatingLeaveURLs = new LinkedList<>();
        meetingsUserParticipating.forEach(meeting -> {
            meetingsUserParticipatingNames.add(meeting.getName());
            meetingsUserParticipatingAboutURLs.add("meetingAbout/id=" + meeting.getId());
            meetingsUserParticipatingLeaveURLs.add("meetingLeave/meetingId=" + meeting.getId() + "/userId=" + loggedUser.getId());
        });
        request.setAttribute("meetings_user_participating_names", meetingsUserParticipatingNames);
        request.setAttribute("meetings_user_participating_about_URLs", meetingsUserParticipatingAboutURLs);
        request.setAttribute("meetings_user_participating_leave_URLs", meetingsUserParticipatingLeaveURLs);
    }

    private void setUserManagingMeetingsAttributes(HttpServletRequest request, User loggedUser, List<Meeting> meetingsUserManaging) {
        List<String> meetingsUserManagingNames = new LinkedList<>();
        List<String> meetingsUserManagingAboutURLs = new LinkedList<>();
        List<String> meetingsUserManagingLeaveURLs = new LinkedList<>();
        meetingsUserManaging.forEach(meeting -> {
            meetingsUserManagingNames.add(meeting.getName());
            meetingsUserManagingAboutURLs.add("meetingAbout/id=" + meeting.getId());
            meetingsUserManagingLeaveURLs.add("meetingLeave/meetingId=" + meeting.getId() + "/userId=" + loggedUser.getId());
        });
        request.setAttribute("meetings_user_managing_names", meetingsUserManagingNames);
        request.setAttribute("meetings_user_managing_about_URLs", meetingsUserManagingAboutURLs);
        request.setAttribute("meetings_user_managing_leave_URLs", meetingsUserManagingLeaveURLs);
    }
}
