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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private boolean isExists;
    private boolean regOK;
    UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");
        request.setAttribute("isExists",isExists);
        request.setAttribute("regSucces",regOK );
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/registration.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        if (regOK){
            regOK=false;
            response.sendRedirect("/login");
            return;
        }
        String login = request.getParameter("name");
        String password = request.getParameter("password");

        if (service.getByName(login)!=null) {
            isExists = true;
            response.sendRedirect("/registration");
            return;
        }
        User user = new User();
        user.setName(login);
        user.setPassword(password);
        user.setImagePath("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png");
        user.setRole("user");

        service.add(user);
        service.update(user);
        regOK=true;

        response.sendRedirect("registration");
    }
}
