package com.inmeetings.presentation;

import com.inmeetings.business.interfaces.UserService;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.presentation.utils.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginCheckServlet", urlPatterns = "/loginCheck")
public class LoginCheckServlet extends HttpServlet {
    @EJB
    private UserService userService;
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authUtils.isUserAlreadyLogged(request))
            response.sendRedirect("mainPage");
        else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            List<User> users = userService.getAllUsers();
            User currUser = null;
            for (User user : users) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    currUser = user;
                    break;
                }
            }

            if (currUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("login", login);

                response.sendRedirect("mainPage");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font><br>");
                rd.include(request, response);
            }
        }
    }
}