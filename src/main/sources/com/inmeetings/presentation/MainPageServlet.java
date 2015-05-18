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

@WebServlet(name = "MainPageServlet", urlPatterns = "/mainPage")
public class MainPageServlet extends HttpServlet {
    @EJB
    private UserService userService;
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!authUtils.isUserAlreadyLogged(request))
            response.sendRedirect("index");
        else {
            String login = (String) request.getSession().getAttribute("login");
            User user = userService.getUserByLogin(login);
            request.setAttribute("first_name", user.getFirstName());
            request.setAttribute("last_name", user.getLastName());
            getServletContext().getRequestDispatcher("/mainPage.jsp").forward(request, response);
        }
    }
}
