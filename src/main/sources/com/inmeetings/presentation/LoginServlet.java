package com.inmeetings.presentation;

import com.inmeetings.presentation.utils.AuthUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @EJB
    private AuthUtils authUtils;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authUtils.isUserAlreadyLogged(request)) {
            response.sendRedirect("mainPage");
            return;
        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}