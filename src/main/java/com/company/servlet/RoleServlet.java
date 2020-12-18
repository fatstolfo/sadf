package com.company.servlet;

import com.company.model.Role;
import com.company.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    RoleService rs;

    @Override
    public void init() throws ServletException {
        rs = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html>\n" +
                "<head>\n" +
                "<title>Текстовое поле</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"role\">\n" +
                "<p><strong>Какова роль?</strong></p>\n" +
                "<input name=\"Role\" type = \"text\"> \n" +
                "<input type = \"submit\"\n"+
                "</form>\n" +
                "</body>\n" +
                "</html>);\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = new Role(req.getParameter("Role"));
        try {
            rs.create(role);
        } catch (Exception ex) {
            System.out.println("exception");
        }
        resp.sendRedirect(req.getContextPath() + "/Role");
    }
}


