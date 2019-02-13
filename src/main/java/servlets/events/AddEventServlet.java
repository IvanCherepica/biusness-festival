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
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/admin/events/add")
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
	    String dateBeginParam = request.getParameter("dateBegin");
	    String dateEndParam = request.getParameter("dateEnd");
	    
	    if (name==null || name.isEmpty() || eventPointIdParam.isEmpty()) {
		    response.sendRedirect("/error.html");
	    }
	    EventPoint eventPoint = null;
	    Festival festival = null;
	    LocalDateTime dateBegin = LocalDateTime.now();
	    LocalDateTime dateEnd = LocalDateTime.now();
	    try {
		    long eventPointId = Long.parseLong(eventPointIdParam);
		    eventPoint = eventPoinService.getById(eventPointId);
		    long festivalId = Long.parseLong(festivalIdParam);
		    festival = festivalService.getById(festivalId);
		    dateBegin = LocalDateTime.parse(dateBeginParam);
		    dateEnd = LocalDateTime.parse(dateEndParam);
		    
		    if (!(eventPoint instanceof EventPoint || festival instanceof Festival)) {
			    response.sendRedirect("/error.html");
		    }
	    } catch (NumberFormatException e) {
		    response.sendRedirect("/error.html");
	    }
        Event event = new Event(name, description, eventPoint, festival);
	    event.setDateBegin(dateBegin);
	    event.setDateEnd(dateEnd);
	    
        eventService.add(event);

        response.setContentType("text/html");
        response.sendRedirect("/admin/festivals");
    }
}