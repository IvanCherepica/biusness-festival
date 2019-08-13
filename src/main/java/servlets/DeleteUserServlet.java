package servlets;

import org.hibernate.HibernateException;
import services.abstraction.UserService;
import services.implementation.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    public DeleteUserServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

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

        response.sendRedirect("/admin/users");
    }

}
