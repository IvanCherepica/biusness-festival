package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null) {
            response.setContentType("text/html");
            response.sendRedirect("/login");
        } else {
            response.setContentType("text/html");
            response.sendRedirect("/user");
        }
    }
}