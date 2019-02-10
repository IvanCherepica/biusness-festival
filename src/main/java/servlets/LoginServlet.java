package servlets;

import models.User;
import services.UserService;
import services.UserServiceImpl;
import services.UserSessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService service = UserServiceImpl.getInstance();
    private UserSessionService userSessionService = UserSessionService.getInstance();
    private boolean isInvalid;

    public LoginServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            isInvalid = true;
            return;
        }

        User user = service.getByName(login);

        if (user == null) {
            isInvalid = true;
            response.sendRedirect("/login");
            return;
        }
        if (user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            userSessionService.addUserSessions(user.getId(),session);
            session.setAttribute("user", user);
            session.setAttribute("userInFestival","false");
            response.setContentType("text/html");
            if (user.getRole().equals("admin")) {
                response.sendRedirect("/admin/festivals"); //исправить на путь к админке
                return;
            }
            response.sendRedirect("/addUser.jsp"); //исправить на путь к странице юзера
        }

        if (!user.getPassword().equals(password) || !user.getName().equals(login)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.setContentType("text/html");
            response.sendRedirect("/error.jsp");
        }
    }
}
