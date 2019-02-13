package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import dto.UserSocketDto;
import models.EventPoint;
import models.Festival;
import services.EventPoinServiceImpl;
import services.FestivalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/map/data_for_festivalList")
public class FestivalDataToMapServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String fesivalID = request.getParameter("festival_id");
        List<EventPoint> eventPoints = null;
        Festival festival = null;
        EventPoinServiceImpl eventPoinService = EventPoinServiceImpl.getInstance();
        eventPoinService.clearCash();

        if (fesivalID != null) {
            Long festivalId = Long.parseLong(fesivalID);
            eventPoints = eventPoinService.getAllEventPointByFestivalId(festivalId);
            festival = FestivalServiceImpl.getInstance().getById(festivalId);
        } else {
            eventPoints = eventPoinService.getAllList();
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
