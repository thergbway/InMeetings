package com.inmeetings.presentation;

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

@WebServlet(name = "LoginCheckServlet", urlPatterns = "/loginCheck")
public class LoginCheckServlet extends HttpServlet {
//    @EJB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("login") && password.equals("password")) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);

            response.sendRedirect("loginSuccess");
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font><br>");
            rd.include(request, response);
        }
    }
}