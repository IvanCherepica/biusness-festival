package servlets;

import com.google.gson.Gson;
import models.Festival;
import services.implementation.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/map")
public class GeolocationMobile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");


        RequestDispatcher dispatcher = request.getRequestDispatcher("/mobileMap.jsp");
        dispatcher.forward(request, response);
    }

}
