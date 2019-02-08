package servlets;

import com.google.gson.Gson;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/map")
public class FestivalMapServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //request.setAttribute("data", geometryJSON);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/map.html");
        dispatcher.forward(request, response);
    }
}
