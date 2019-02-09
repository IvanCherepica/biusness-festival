package servlets;
import com.google.gson.Gson;
import dao.FestivalDaoImpl;
import models.Festival;
import models.User;
import services.FestivalService;
import services.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addFest")
public class AddFestivalServlet extends HttpServlet {
    private final FestivalService festivalad=  FestivalServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addFest.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("name");
        String description = request.getParameter("description");
        String geomertyJson = request.getParameter("geometry");

        Festival fest = new Festival(login, description, geomertyJson, "black");
        festivalad.add(fest);


        response.setContentType("text/html");
        response.sendRedirect("/festivals");
		
    }
}