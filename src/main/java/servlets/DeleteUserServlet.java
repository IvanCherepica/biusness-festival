package servlets;

import org.hibernate.HibernateException;
import services.FestivalService;
import services.FestivalServiceImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String paramId = request.getParameter("delete");

        if (paramId == null) {
            response.sendRedirect("/error.html");
        } else {
            Long id = Long.parseLong(paramId);
            try {
                userService.remove(id);
            } catch (HibernateException e) {
                response.sendRedirect("/error.html");
            }
        }
        response.setContentType("text/html");
        response.sendRedirect("/admin/users");
    }

}
