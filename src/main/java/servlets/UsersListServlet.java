package servlets;


import models.User;
import services.implementation.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UsersListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        List<User> userList = UserServiceImpl.getInstance().getAllList();
        request.setAttribute("usersList", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/usersList.jsp");
        dispatcher.forward(request, response);
    }
}
