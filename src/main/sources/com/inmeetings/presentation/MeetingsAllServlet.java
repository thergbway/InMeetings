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

        User user = authUtils.getLoggedUser(request);
        List<Meeting> meetings = meetingService.getAllMeetingsForUser(user);

        List<String> meetingsNames = new LinkedList<>();
        List<String> meetingsAboutURLs = new LinkedList<>();
        List<String> meetingsLeaveURLs = new LinkedList<>();

        meetings.forEach(meeting -> {
            meetingsNames.add(meeting.getName());
            meetingsAboutURLs.add("meetingAbout/id=" + meeting.getId());
            meetingsLeaveURLs.add("meetingLeave/meetingId=" + meeting.getId() + "/userId=" + user.getId());
        });

        request.setAttribute("meetings_names", meetingsNames);
        request.setAttribute("meetings_about_URLs", meetingsAboutURLs);
        request.setAttribute("meetings_leave_URLs", meetingsLeaveURLs);
        request.setAttribute("first_name", user.getFirstName());
        request.setAttribute("last_name", user.getLastName());

        getServletContext().getRequestDispatcher("/meetingsAll.jsp").forward(request, response);

    }
}
