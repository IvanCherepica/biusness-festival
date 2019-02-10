package servlets;

import models.Festival;
import services.UserFounderService;
import services.UserFounderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/location")
public class CompearLocationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String point = "60.11135698961218 30.26821981436865";
        String point1 = "60.11048170467497 30.26865757895814";
        String point2 = "60.105365488398284 30.26574157732864";

        Festival festival = new Festival("Test", "Testing", "Red" ,
                "Some", "60.11173060613703 30.267900556923905", 75);
        UserFounderService service = new UserFounderServiceImpl(request.getSession());
        service.isUserInFestival(point,festival);
        response.setContentType("text/html");
        response.getWriter().println(request.getSession().getAttribute("isInUnit"));
    }
}
