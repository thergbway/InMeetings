package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.ManagerService;
import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.business.interfaces.ParticipantService;
import com.inmeetings.business.utils.TimeUtils;
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
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "MeetingAboutServlet", urlPatterns = "meetingAbout/*")
public class MeetingAboutServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;
    @EJB
    private TimeUtils timeUtils;
    @EJB
    private MeetingService meetingService;
    @EJB
    private ParticipantService participantService;
    @EJB
    private ManagerService managerService;

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

        Meeting meeting = meetingService.getById(id);
        List<Participant> participants = participantService.getParticipantsOfMeeting(meeting);
        List<Manager> managers = managerService.getManagersOfMeeting(meeting);

        setLoggedUserAttributes(request);
        setMeetingAttributes(request, meeting);
        setManagerAttributes(request, managers);
        setParticipantAttributes(request, participants);

        getServletContext().getRequestDispatcher("/meetingAbout.jsp").forward(request, response);
    }

    private void setLoggedUserAttributes(HttpServletRequest request) {
        User loggedUser = authUtils.getLoggedUser(request);
        request.setAttribute("logged_user_id", loggedUser.getId());
        request.setAttribute("logged_user_first_name", loggedUser.getFirstName());
        request.setAttribute("logged_user_last_name", loggedUser.getLastName());
    }

    private void setManagerAttributes(HttpServletRequest request, List<Manager> managers) {
        List<String> managersNames = new LinkedList<>();
        List<String> managersURLs = new LinkedList<>();

        managers.forEach(manager -> {
            User user = manager.getUser();
            managersNames.add(user.getFirstName() + " " + user.getLastName());
            managersURLs.add(request.getContextPath() + "/userAbout/id=" + user.getId());
        });
        request.setAttribute("managers_names", managersNames);
        request.setAttribute("managers_URLs", managersURLs);
    }

    private void setParticipantAttributes(HttpServletRequest request, List<Participant> participants) {
        List<String> participantsNames = new LinkedList<>();
        List<String> participantsURLs = new LinkedList<>();

        participants.forEach(participant -> {
            User user = participant.getUser();
            participantsNames.add(user.getFirstName() + " " + user.getLastName());
            participantsURLs.add(request.getContextPath() + "/userAbout/id=" + user.getId());
        });
        request.setAttribute("participants_names", participantsNames);
        request.setAttribute("participants_URLs", participantsURLs);
    }

    private void setMeetingAttributes(HttpServletRequest request, Meeting meeting) {
        request.setAttribute("meeting_name", meeting.getName());
        request.setAttribute("meeting_description", meeting.getDescription());
        request.setAttribute("meeting_start", timeUtils.convertToSystemDefaultDateTime(meeting.getStartTime()));
        request.setAttribute("meeting_end", timeUtils.convertToSystemDefaultDateTime(meeting.getEndTime()));
    }
}
