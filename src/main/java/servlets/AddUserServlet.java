package servlets;

import models.User;
import services.abstraction.UserService;
import services.implementation.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {
    private final UserService service = UserServiceImpl.getInstance();
    boolean isExists;
    boolean isInvalid;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");
        request.setAttribute("isExists",isExists);
        request.setAttribute("isInvalid",isInvalid);
        String paramId = request.getParameter("edit");
        User user;

        if (paramId == null) {
            user = new User("name", "password", "role");
        } else {
            Long id = Long.parseLong(paramId);
            user = service.getById(id);
        }
        request.setAttribute("user", user);

        isExists = false;
        isInvalid = false;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/userAdd.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (service.getByName(name)!=null) {
            isExists = true;
            response.sendRedirect("/admin/addUser?");
            return;
        }

        if (name == null || "".equals(name) || password==null || "".equals(password)) {
            isInvalid = true;
            response.sendRedirect("/admin/addUser");
            return;
        }
        User user = new User(name, password, role);
        user.setImagePath("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png");

        service.add(user);


        response.sendRedirect("/admin/users");
    }
}
