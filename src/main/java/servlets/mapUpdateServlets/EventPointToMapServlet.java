package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import models.EventPoint;
import services.implementation.EventPoinServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/event-to-map")
public class EventPointToMapServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fesivalID = request.getParameter("festival_id");
        List<EventPoint> eventPoints = null;
        if (fesivalID != null) {
            Long festivalId = Long.parseLong(fesivalID);
            eventPoints = EventPoinServiceImpl.getInstance().getAllEventPointByFestivalId(festivalId);
        } else {
            eventPoints = EventPoinServiceImpl.getInstance().getAllList();
        }
        //List<EventPoint> eventPoints = EventPoinServiceImpl.getInstance().getAllEventPointByFestivalId(festivalId);
        //request.setAttribute("eventPoints", eventPoints);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String gson = new Gson().toJson(eventPoints);
        response.getWriter().write(gson);

    }
}
