package com.twilio.authy2fa.servlet;

import com.twilio.authy2fa.lib.SessionManager;
import com.twilio.authy2fa.models.User;
import com.twilio.authy2fa.models.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {

    private static final SessionManager sessionManager = new SessionManager();
    private static final UserService service = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long userId = sessionManager.LoggedUserId(request);
        User user = service.find(userId);

        request.setAttribute("name", user.getName());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("phoneNumber", user.getPhoneNumber());
        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }
}