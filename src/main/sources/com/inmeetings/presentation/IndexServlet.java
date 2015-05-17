package com.inmeetings.presentation;

import com.inmeetings.presentation.util.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (AuthUtils.isUserAlreadyLogged(request))
            response.sendRedirect("mainPage");
        else
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
