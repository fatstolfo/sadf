package com.company.servlet;

import com.company.model.User;
import com.company.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/register")
public class RegServlet extends HttpServlet {
    UserService us;

    @Override
    public void init() throws ServletException {
        us = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html> \n" +
                "<head> \n" +
                "<body> \n" +
                "<form method=\"post\" action=\"register\"> \n" +
                "<input name=\"password\" type = \"password\"> \n" +
                "<input name=\"email\" type = \"text\"> \n" +
                "<input type=\"submit\"> \n" +
                "</form> \n" +
                "</body> \n" +
                "</head> \n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("email"));
        try {
            us.create(user);
        } catch (Exception ex) {
            System.out.println("exception");
        }

        resp.sendRedirect(req.getContextPath() + "/register");
    }
}
