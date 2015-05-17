package com.inmeetings.presentation.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtils {
    private AuthUtils() {
    }

    public static boolean isUserAlreadyLogged(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        return login != null;
    }
}
