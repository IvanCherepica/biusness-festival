package servlets;


import models.User;
import services.UserServiceImpl;
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

        List<User> userList = UserServiceImpl.getInstance().getAllList();
        request.setAttribute("usersList", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/usersList.jsp");
        dispatcher.forward(request, response);
    }
}
