package com.example.view;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class.getCanonicalName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("zebsoft") && password.equals("1234")) {
            HttpSession session = req.getSession();
            if (session != null) {
                session.setAttribute("username", "zebsoft");
                session.setMaxInactiveInterval(600);
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard.do");
            }
        } else {
            // Invalid credentials
            resp.sendRedirect("login.jsp?error="
                    + URLEncoder.encode("Invalid username or password. Please try again.", StandardCharsets.UTF_8));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String redirectionPath = req.getContextPath() + "/admin/dashboard-ctrl";
            logger.info("Redirecting to: " + redirectionPath);
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard-ctrl");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
