package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.business.interfaces.UserService;
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

@WebServlet(name = "UserAboutServlet", urlPatterns = "userAbout/*")
public class UserAboutServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;
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

        Integer id = null;
        try {
            String idAsStr = request.getPathInfo().split("id=")[1];
            id = Integer.parseInt(idAsStr);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        User user = userService.getById(id);
        List<Meeting> meetingsUserParticipating = meetingService.getMeetingsUserParticipating(user);
        List<Meeting> meetingsUserManaging = meetingService.getMeetingsUserManaging(user);

        setUserAttributes(request, user);
        setLoggedUserAttributes(request);
        setMeetingsUserParticipatingAttributes(request, meetingsUserParticipating);
        setMeetingsUserManagingAttributes(request, meetingsUserManaging);

        getServletContext().getRequestDispatcher("/userAbout.jsp").forward(request, response);
    }

    private void setLoggedUserAttributes(HttpServletRequest request) {
        User loggedUser = authUtils.getLoggedUser(request);
        request.setAttribute("logged_user_id", loggedUser.getId());
        request.setAttribute("logged_user_first_name", loggedUser.getFirstName());
        request.setAttribute("logged_user_last_name", loggedUser.getLastName());
    }

    private void setMeetingsUserManagingAttributes(HttpServletRequest request, List<Meeting> meetingsUserManaging) {
        List<String> meetingsManagingNames = new LinkedList<>();
        List<String> meetingsManagingURLs = new LinkedList<>();
        meetingsUserManaging.forEach(meeting -> {
            meetingsManagingNames.add(meeting.getName());
            meetingsManagingURLs.add(request.getContextPath() + "/meetingAbout/id=" + meeting.getId());
        });
        request.setAttribute("meetings_managing_count", meetingsManagingNames.size());
        request.setAttribute("meetings_managing_names", meetingsManagingNames);
        request.setAttribute("meetings_managing_URLs", meetingsManagingURLs);
    }

    private void setMeetingsUserParticipatingAttributes(HttpServletRequest request, List<Meeting> meetingsUserParticipating) {
        List<String> meetingsParticipatingNames = new LinkedList<>();
        List<String> meetingsParticipatingURLs = new LinkedList<>();
        meetingsUserParticipating.forEach(meeting -> {
            meetingsParticipatingNames.add(meeting.getName());
            meetingsParticipatingURLs.add(request.getContextPath() + "/meetingAbout/id=" + meeting.getId());
        });
        request.setAttribute("meetings_participating_count", meetingsParticipatingNames.size());
        request.setAttribute("meetings_participating_names", meetingsParticipatingNames);
        request.setAttribute("meetings_participating_URLs", meetingsParticipatingURLs);
    }

    private void setUserAttributes(HttpServletRequest request, User user) {
        request.setAttribute("first_name", user.getFirstName());
        request.setAttribute("last_name", user.getLastName());
        request.setAttribute("role_name", user.getRole().getRoleName());
    }
}