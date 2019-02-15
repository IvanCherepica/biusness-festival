package servlets;

import models.User;
import services.abstraction.UserService;
import services.implementation.UserServiceImpl;
import services.userNotificationServices.UserSessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService service = UserServiceImpl.getInstance();
    private UserSessionService userSessionService = UserSessionService.getInstance();
    private boolean isInvalid;
    private boolean isPass;

    public LoginServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        request.setAttribute("isInvalid",isInvalid);
        request.setAttribute("isPass", isPass);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        isInvalid=false;
        isPass=false;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

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
            if (user.getRole().equals("admin")) {
                response.sendRedirect("/admin/festivals"); //исправить на путь к админке
                return;
            }
            response.sendRedirect("/user");
        }

        if (!user.getPassword().equals(password)) {
            isPass = true;
            response.sendRedirect("/login");
            return;
        }
    }
}
