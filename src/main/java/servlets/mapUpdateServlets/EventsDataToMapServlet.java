package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import dto.UserSocketDto;
import models.Event;
import models.EventPoint;
import services.implementation.EventPoinServiceImpl;
import services.implementation.EventServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/map/data_for_eventPointList")
public class EventsDataToMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String eventPointID = request.getParameter("eventPoint_id");
        List<Event> events = null;
        EventPoint eventPoint = null;
        EventPoinServiceImpl eventPoinService = EventPoinServiceImpl.getInstance();


        if (eventPointID != null) {
            Long eventPointIdL = Long.parseLong(eventPointID);
            events = EventServiceImpl.getInstance().getAllByEventPoint(eventPointIdL);
            eventPoint = EventPoinServiceImpl.getInstance().getById(eventPointIdL);
        } else {
            events = EventServiceImpl.getInstance().getAllList();
        }

        String eventPointsGson = "";


//        for (Object currentEventPoint : eventPoints) {
//            eventPointsGson = new Gson().toJson((EventPoint)currentEventPoint);
//        }
        UserSocketDto userSocketDto = new UserSocketDto();
        userSocketDto.setEventPoint(eventPoint);
        userSocketDto.setEventList(events);

//        eventPointsGson = new Gson().toJson(eventPoints);
//        userSocketDto.setEventPointsGson(eventPointsGson);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String gson = new Gson().toJson(userSocketDto);
        response.getWriter().write(gson);
    }
}
