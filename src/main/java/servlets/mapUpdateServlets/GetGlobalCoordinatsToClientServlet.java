package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import dto.GetGlobalCoordinatsDto;
import dto.UserSocketDto;
import util.GeoDataHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/get_coordinats_to_client")
public class GetGlobalCoordinatsToClientServlet  extends HttpServlet {
    GeoDataHolder geoDataHolder = GeoDataHolder.getGeoDataHolder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        double longitudeX = geoDataHolder.getLongitude();
        double latitudeY = geoDataHolder.getLatitude();

        //geoDataHolder.setUserGeoposition(longitudeX + " " + latitudeY);

        GetGlobalCoordinatsDto dto = new GetGlobalCoordinatsDto();
        dto.setLongitudeX(longitudeX);
        dto.setLatitudeY(latitudeY);

        String json = new Gson().toJson(dto);

        response.setContentType("application/json");
        //response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        System.out.println("Coordinats sent");
    }
}
