package servlets.adminMap;

import com.google.gson.Gson;
import dto.GetGlobalCoordinatsDto;
import util.GeoDataHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/getUsersCoordinates")
public class GetGlobalCoordinatsToClientServlet  extends HttpServlet {
    GeoDataHolder geoDataHolder = GeoDataHolder.getGeoDataHolder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //x - latitude, y - longitude

        double longitudeY = geoDataHolder.getLongitude();
        double latitudeX = geoDataHolder.getLatitude();
        long currentUserFestivalId = geoDataHolder.getCurrentFestivalId();


        GetGlobalCoordinatsDto dto = new GetGlobalCoordinatsDto();
        dto.setLongitudeY(longitudeY);
        dto.setLatitudeX(latitudeX);
        dto.setUserCurrentFestivalId(currentUserFestivalId);

        String json = new Gson().toJson(dto);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        System.out.println("Coordinates sent");
    }
}