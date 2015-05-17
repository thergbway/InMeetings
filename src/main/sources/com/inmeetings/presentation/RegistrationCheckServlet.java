package com.inmeetings.presentation;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;
import com.inmeetings.persistence.dao.interfaces.UserDAO;
import com.inmeetings.presentation.util.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationCheckServlet", urlPatterns = "/registrationCheck")
public class RegistrationCheckServlet extends HttpServlet {
    @EJB
    private UserDAO userDAO;
    @EJB
    private RoleDAO roleDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (AuthUtils.isUserAlreadyLogged(request))
            response.sendRedirect("mainPage");
        else {
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            try {
                Role userRole = roleDAO.getUserRole();
                userDAO.create(new User(userRole, login, password, firstName, lastName));
            } catch (Exception e) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Registration error</font><br>");
                rd.include(request, response);
                return;
            }

            getServletContext().getRequestDispatcher("/registrationSuccess.jsp").forward(request, response);
        }
    }
}