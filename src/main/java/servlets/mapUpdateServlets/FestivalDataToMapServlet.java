package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import dto.UserSocketDto;
import models.EventPoint;
import models.Festival;
import services.implementation.EventPoinServiceImpl;
import services.implementation.FestivalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/map/data_for_festivalList")
public class FestivalDataToMapServlet  extends HttpServlet {
    private FestivalServiceImpl festivalService = FestivalServiceImpl.getInstance();
    private EventPoinServiceImpl eventPointService = EventPoinServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fesivalID = request.getParameter("festival_id");
        List<EventPoint> eventPoints = null;
        Festival festival = null;


        if (fesivalID != null) {
            Long festivalId = Long.parseLong(fesivalID);
            eventPoints = eventPointService.getAllEventPointByFestivalId(festivalId);
            festival = festivalService.getById(festivalId);

        } else {
            eventPoints = eventPointService.getAllList();
        }

        String eventPointsGson = "";


//        for (Object currentEventPoint : eventPoints) {
//            eventPointsGson = new Gson().toJson((EventPoint)currentEventPoint);
//        }


        UserSocketDto userSocketDto = new UserSocketDto();
        userSocketDto.setFestival(festival);
        userSocketDto.setEventPointList(eventPoints);

        eventPointsGson = new Gson().toJson(eventPoints);
        userSocketDto.setEventPointsGson(eventPointsGson);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String gson = new Gson().toJson(userSocketDto);
        response.getWriter().write(gson);

    }
}
