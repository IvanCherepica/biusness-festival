package servlets.events;

import models.Event;
import models.EventPoint;
import models.Festival;
import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/events/addEvent")
public class AddEventServlet extends HttpServlet {
    private final EventService eventService =  EventServiceImpl.getInstance();
	private final EventPoinService eventPoinService = EventPoinServiceImpl.getInstance();
	private final FestivalService festivalService=  FestivalServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String festivalIdParam = request.getParameter("festivalId");
	    String eventPointIdParam = request.getParameter("eventPointId");
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    
	    
	    if (name==null || name.isEmpty() || eventPointIdParam.isEmpty()) {
		    response.sendRedirect("/error.html");
	    }
	    EventPoint eventPoint = null;
	    Festival festival = null;
	    try {
		    long eventPointId = Long.parseLong(eventPointIdParam);
		    eventPoint = eventPoinService.getById(eventPointId);
		    long festivalId = Long.parseLong(festivalIdParam);
		    festival = festivalService.getById(festivalId);
		    
		    if (!(eventPoint instanceof EventPoint || festival instanceof Festival)) {
			    response.sendRedirect("/error.html");
		    }
	    } catch (NumberFormatException e) {
		    response.sendRedirect("/error.html");
	    }
        Event event = new Event(name, description, eventPoint, festival);
	    
        eventService.add(event);

        response.setContentType("text/html");
        response.sendRedirect("/admin/festivals");
    }
}