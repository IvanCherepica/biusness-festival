package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/compareLocations")
public class CompaerLocationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        System.out.println(request.getParameter("xPosition"));
        System.out.println(request.getParameter("yPosition"));

        response.getWriter().write("[[60.112539461325454, 30.266258256820947], [60.11261430744659, 30.267031399988582]]");
    }
}
