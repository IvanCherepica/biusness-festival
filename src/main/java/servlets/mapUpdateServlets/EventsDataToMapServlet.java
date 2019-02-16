package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EventListDto;
import dto.UserSocketDto;
import models.Event;
import models.EventPoint;
import services.implementation.EventPoinServiceImpl;
import services.implementation.EventServiceImpl;
import util.EventListDtoSerealiser;
import util.UserSocketSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/map/data_for_eventPointList")
public class EventsDataToMapServlet extends HttpServlet {
    private EventPoinServiceImpl eventPoinService = EventPoinServiceImpl.getInstance();
    private EventServiceImpl eventService = EventServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String eventPointID = request.getParameter("eventPoint_id");
        Long userId = Long.parseLong(request.getParameter("userId"));
        List<Event> events = null;
        EventPoint eventPoint = null;



        if (eventPointID != null) {
            Long eventPointIdL = Long.parseLong(eventPointID);
            events = eventService.getAllByEventPoint(eventPointIdL);
            eventPoint = eventPoinService.getById(eventPointIdL);
        } else {
            events = EventServiceImpl.getInstance().getAllList();
        }

        String eventPointsGson = "";


//        for (Object currentEventPoint : eventPoints) {
//            eventPointsGson = new Gson().toJson((EventPoint)currentEventPoint);
//        }
        EventListDto eventListDto = new EventListDto();
        eventListDto.setEventPoinName(eventPoint.getName());
        eventListDto.setEventPoinDescription(eventPoint.getDescription());
        eventListDto.setEventList(events);
        eventListDto.setUserId(userId);


//        eventPointsGson = new Gson().toJson(eventPoints);
//        userSocketDto.setEventPointsGson(eventPointsGson);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(EventListDto.class, new EventListDtoSerealiser())
                .create();

        response.getWriter().write(gson.toJson(eventListDto));
    }
}
