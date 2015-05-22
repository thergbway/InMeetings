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

@WebServlet(name = "MeetingsForParticipatingServlet", urlPatterns = "/meetingsForParticipating")
public class MeetingsForParticipatingServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;
    @EJB
    private MeetingService meetingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }

        User loggedUser = authUtils.getLoggedUser(request);
        List<Meeting> meetingsUserNotInvolved = meetingService.getMeetingsUserNotInvolved(loggedUser);
        setMeetingsUserManagingAttributes(request, meetingsUserNotInvolved);
        setLoggedUserAttributes(request, loggedUser);

        getServletContext().getRequestDispatcher("/meetingsForParticipating.jsp").forward(request, response);
    }

    private void setLoggedUserAttributes(HttpServletRequest request, User loggedUser) {
        request.setAttribute("logged_user_first_name", loggedUser.getFirstName());
        request.setAttribute("logged_user_last_name", loggedUser.getLastName());
        request.setAttribute("logged_user_id", loggedUser.getId());
    }

    private void setMeetingsUserManagingAttributes(HttpServletRequest request, List<Meeting> meetingsUserNotInvolved) {
        List<String> meetingsNames = new LinkedList<>();
        List<String> meetingsURLsAbout = new LinkedList<>();
        List<String> meetingsURLsParticipate = new LinkedList<>();

        meetingsUserNotInvolved.forEach(meeting -> {
            meetingsNames.add(meeting.getName());
            meetingsURLsAbout.add("meetingAbout/id=" + meeting.getId());
            meetingsURLsParticipate.add("meetingParticipate/id=" + meeting.getId());
        });

        request.setAttribute("meetings_names", meetingsNames);
        request.setAttribute("meetings_URLs_about", meetingsURLsAbout);
        request.setAttribute("meetings_URLs_participate", meetingsURLsParticipate);
    }
}
