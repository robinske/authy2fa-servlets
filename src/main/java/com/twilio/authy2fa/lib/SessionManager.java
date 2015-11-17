package com.twilio.authy2fa.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

    private static final int MAX_INACTIVE_INTERVAL = 30 * 60;

    public void LogIn(HttpServletRequest request, long userId) {
        HttpSession session = request.getSession();
        session.setAttribute("authenticated", true);
        session.setAttribute("userId", userId);
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    public void LogOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public long LoggedUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (long) session.getAttribute("userId");
        }

        return 0;
    }
}