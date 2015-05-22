package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.UserService;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.presentation.utils.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MeetingCreationSuccessServlet", urlPatterns = "meetingCreationSuccess")
public class MeetingCreationSuccessServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect("index");
            return;
        }
        setLoggedUserAttributes(request);

        getServletContext().getRequestDispatcher("/meetingCreationSuccess.jsp").forward(request, response);
    }

    private void setLoggedUserAttributes(HttpServletRequest request) {
        User loggedUser = authUtils.getLoggedUser(request);
        request.setAttribute("logged_user_id", loggedUser.getId());
        request.setAttribute("logged_user_first_name", loggedUser.getFirstName());
        request.setAttribute("logged_user_last_name", loggedUser.getLastName());
    }
}