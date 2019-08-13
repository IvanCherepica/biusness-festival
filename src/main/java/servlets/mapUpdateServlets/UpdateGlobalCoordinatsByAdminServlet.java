package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import dto.UserSocketDto;
import models.User;
import util.GeoDataHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/geoposition")
public class UpdateGlobalCoordinatsByAdminServlet extends HttpServlet {

    GeoDataHolder geoDataHolder = GeoDataHolder.getGeoDataHolder();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

//        HttpSession session = request.getParameter()getSession();
        String longitudeX = request.getParameter("longitude"); //session.getAttribute("longitude");
        String latitudeY = request.getParameter("latitude");//(String) session.getAttribute("latitude");

        geoDataHolder.setUserGeoposition(longitudeX + " " + latitudeY);

        geoDataHolder.setLongitude(Double.parseDouble(longitudeX));
        geoDataHolder.setLatitude(Double.parseDouble(latitudeY));

        System.out.println("coordinats from mobile : " + longitudeX + " " + latitudeY);


//        response.setContentType("application/json");
//        //response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
    }
}
