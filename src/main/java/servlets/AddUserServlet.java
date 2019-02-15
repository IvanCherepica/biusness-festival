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
    private boolean isExist;
    private final UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String paramId = request.getParameter("edit");
        User user;

        if (paramId == null) {
            user = new User("name", "password", "role");
        } else {
            Long id = Long.parseLong(paramId);
            user = userService.getById(id);
        }
        request.setAttribute("user", user);
        request.setAttribute("isExist",isExist);

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

//        if (name == null || name.isEmpty()) {
//            response.sendRedirect("/error.html");
//        }
        if (userService.getByName(name)!=null){
            isExist=true;
            response.sendRedirect("/admin/addUser");
            return;
        }
        User user = new User(name, password, role);
        user.setImagePath("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png");

        userService.add(user);


        response.sendRedirect("/admin/users");
    }
}
