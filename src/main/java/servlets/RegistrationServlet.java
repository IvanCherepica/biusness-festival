package servlets;

import models.User;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        if(login.isEmpty()|| password.isEmpty()){
            response.sendRedirect("/registration");
            return;
        }
        User user = new User();
        user.setName(login);
        user.setPassword(password);
        user.setImagePath("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png");
        user.setRole("user");
        service.add(user);
        response.sendRedirect("/login");
    }
}
