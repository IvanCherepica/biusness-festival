package servlets.adminMap;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/mapWithUsers")
public class AdminMapWithUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;UTF-8");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adminMapWithUsers.jsp");
        dispatcher.forward(request, response);
    }
}
