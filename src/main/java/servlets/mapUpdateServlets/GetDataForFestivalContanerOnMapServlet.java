package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.FestivalDataDto;
import dto.UserSocketDto;
import models.Event;
import models.EventPoint;
import models.Festival;
import services.implementation.EventPoinServiceImpl;
import services.implementation.EventServiceImpl;
import services.implementation.FestivalServiceImpl;
import util.FestivalDataToMapSerealializer;
import util.UserSocketSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/data_for_festival")
public class GetDataForFestivalContanerOnMapServlet extends HttpServlet {
    private FestivalServiceImpl festivalService = FestivalServiceImpl.getInstance();
    private EventPoinServiceImpl eventPointService = EventPoinServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fesivalID = request.getParameter("fesivalId");

        Long festivalId = Long.parseLong(fesivalID);
        List<EventPoint>  eventPoints = eventPointService.getAllEventPointByFestivalId(festivalId);
        Festival festival = festivalService.getById(festivalId);


        String eventPointsGson = "";


        FestivalDataDto festivalDataDto = new FestivalDataDto();

        festivalDataDto.setFestival(festival);
        festivalDataDto.setPointList(eventPoints);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(FestivalDataDto.class, new FestivalDataToMapSerealializer())
                .create();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(festivalDataDto));

    }
}
