package com.inmeetings.presentation;

import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.interfaces.RoleDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleServlet", urlPatterns = "/roles")
public class RoleServlet extends HttpServlet {
    @EJB
    private RoleDAO roleDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        roleDAO.create(new Role("servlet-role" + System.currentTimeMillis()));
        roleDAO.create(new Role("servlet-role2" + System.currentTimeMillis()));
        roleDAO.create(new Role("servlet-role3" + System.currentTimeMillis()));

        List<Role> allRoles = roleDAO.getAllRoles();
        int rolesCount = allRoles.size();

//
//        PrintWriter out = response.getWriter();
//        out.print("<h1>Hello Servlet</h1>");
//        allRoles.forEach(role -> out.print(role + "<br>"));

        request.getSession().setAttribute("count", rolesCount);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}