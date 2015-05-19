package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.ManagerService;
import com.inmeetings.business.interfaces.MeetingService;
import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.presentation.utils.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet(name = "MeetingCreationCheckServlet", urlPatterns = "/meetingCreationCheck")
public class MeetingCreationCheckServlet extends HttpServlet {
    @EJB
    private MeetingService meetingService;
    @EJB
    private ManagerService managerService;
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect("index");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String position_description = request.getParameter("position_description");

        try {
            if(name.equals(""))
                throw new Exception("Name must not be empty!");

            Meeting meeting = new Meeting(name, new Timestamp(Long.parseLong(start)),
                    new Timestamp(Long.parseLong(end)), description);
            User user = authUtils.getLoggedUser(request);
            managerService.save(new Manager(meeting, user, position_description));
        } catch (Exception e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/meetingCreate.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Creation error!</font><br>");
            rd.include(request, response);
            return;
        }

        response.sendRedirect("meetingCreationSuccess");
    }
}
