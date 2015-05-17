package com.inmeetings.presentation;

import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.interfaces.UserDAO;
import com.inmeetings.presentation.util.AuthUtils;

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
    private UserDAO userDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!AuthUtils.isUserAlreadyLogged(request))
            response.sendRedirect("index");
        else {
            String login = (String) request.getSession().getAttribute("login");
            User user = userDAO.getUserByLogin(login);
            request.setAttribute("first_name", user.getFirstName());
            request.setAttribute("last_name", user.getLastName());
            getServletContext().getRequestDispatcher("/mainPage.jsp").forward(request, response);
        }
    }
}
