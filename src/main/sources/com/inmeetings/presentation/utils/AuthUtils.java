package com.inmeetings.presentation.utils;

import com.inmeetings.business.interfaces.UserService;
import com.inmeetings.persistence.dao.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Stateless
public class AuthUtils {
    @EJB
    private UserService userService;

    public AuthUtils() {
    }

    public boolean isUserAlreadyLogged(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        return login != null;
    }

    public User getLoggedUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        return userService.getUserByLogin(login);
    }
}
