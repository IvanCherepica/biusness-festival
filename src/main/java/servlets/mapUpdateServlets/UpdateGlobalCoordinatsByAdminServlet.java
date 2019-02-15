package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import dto.UserSocketDto;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/geoposition")
public class UpdateGlobalCoordinatsByAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String longitudeX = (String) session.getAttribute("longitude");
        String latitudeY = (String) session.getAttribute("latitude");




//        response.setContentType("application/json");
//        //response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
    }
}
