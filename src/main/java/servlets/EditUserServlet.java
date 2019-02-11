package servlets;

import models.User;
import org.hibernate.HibernateException;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String paramId = request.getParameter("edit");
        User user;

        if (paramId == null) {
            response.sendRedirect("/error.html");
        } else {
            long id = Long.parseLong(paramId);
            user = userService.getById(id);
            request.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paramId = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Long id = Long.parseLong(paramId);
            User user = userService.getById(id);
            user.setName(name == null ? "" : name);
            user.setPassword(password == null ? "" : password);
            user.setRole(role == null ? "" : role);

            userService.update(user);

            response.setContentType("text/html");
            response.sendRedirect("/admin/users");
        } catch (HibernateException | NumberFormatException e) {
            response.sendRedirect("/error.html");
        }
    }
}
