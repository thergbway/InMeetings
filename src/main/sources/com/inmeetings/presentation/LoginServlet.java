package com.inmeetings.presentation;

import com.inmeetings.presentation.util.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (AuthUtils.isUserAlreadyLogged(request))
            response.sendRedirect("mainPage");
        else
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}