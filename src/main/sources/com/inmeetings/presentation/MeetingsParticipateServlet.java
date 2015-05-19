package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.business.interfaces.ParticipantService;
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

@WebServlet(name = "MeetingsParticipateServlet", urlPatterns = "meetingParticipate/*")
public class MeetingsParticipateServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;
    @EJB
    private MeetingService meetingService;
    @EJB
    private ParticipantService participantService;

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

        User user = authUtils.getLoggedUser(request);
        Meeting meeting = meetingService.getById(id);
        participantService.create(new Participant(meeting, user));

        response.sendRedirect(request.getContextPath() + "/meetingsForParticipating");
    }
}
