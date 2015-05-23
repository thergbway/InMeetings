package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.RoleService;
import com.inmeetings.business.interfaces.UserService;
import com.inmeetings.persistence.dao.entities.Role;
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

@WebServlet(name = "RegistrationCheckServlet", urlPatterns = "/registrationCheck")
public class RegistrationCheckServlet extends HttpServlet {
    @EJB
    private UserService userService;
    @EJB
    private RoleService roleService;
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect("mainPage");
            return;
        }
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Role userRole = roleService.getUserRole();
            userService.save(new User(userRole, login, password, firstName, lastName));
        } catch (Exception e) {
            request.setAttribute("error_message", "<font color=red> Try again! Your type is wrong!</font>");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/registrationSuccess.jsp").forward(request, response);

    }
}