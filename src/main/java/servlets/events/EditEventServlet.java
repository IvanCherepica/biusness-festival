package servlets.events;

import models.Event;
import models.EventPoint;
import org.hibernate.HibernateException;
import services.abstraction.EventPoinService;
import services.abstraction.EventService;
import services.implementation.EventPoinServiceImpl;
import services.implementation.EventServiceImpl;
import util.DateTimeConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/admin/events/edit")
public class EditEventServlet extends HttpServlet {
	private final EventPoinService eventPoinService =  EventPoinServiceImpl.getInstance();
	private final EventService eventService =  EventServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;UTF-8");
//		String paramId = request.getParameter("festivalId");
//		Festival festival;
//
//		List<String> test = new ArrayList<>();
//
//		if (paramId==null) {
//			response.sendRedirect("/error.html");
//		} else {
//			long id = Long.parseLong(paramId);
//			List<EventPoint> eventPoints = eventPoinService.getAllByFestival(id);
//			request.setAttribute("festival", festival);
//			//request.setAttribute("eventPointsList", eventPoints);
//			request.setAttribute("eventPointsList", eventPoints);
//			request.setAttribute("hotPointList", hotPoints);
//		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/festivalEdit.jsp");
//		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		String festivalIdParam = request.getParameter("festivalId");
		String eventIdParam = request.getParameter("eventId");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String eventPointIdParam = request.getParameter("eventPointId");
//		String dateBeginParam = request.getParameter("dateBegin");
//		String dateEndParam = request.getParameter("dateEnd");
		
		LocalDateTime dateBegin = LocalDateTime.now();
		LocalDateTime dateEnd = LocalDateTime.now();
		
		try {
			long festivalId = Long.parseLong(festivalIdParam);
			long eventId = Long.parseLong(eventIdParam);
			long eventPointId = Long.parseLong(eventPointIdParam);
			
//			dateBegin = DateTimeConverter.parse(dateBeginParam);
//			dateEnd = DateTimeConverter.parse(dateEndParam);
			
			EventPoint eventPoint = eventPoinService.getById(eventPointId);
			
			Event event = eventService.getById(eventId);
			
			event.setName(name == null ? "" : name);
			event.setDescription(description == null ? "" : description);
			event.setEventPoint(eventPoint);
//			event.setDateBegin(dateBegin);
//			event.setDateEnd(dateEnd);
			
			eventService.update(event);

			//response.sendRedirect("/admin/editFestival?festivalId="+festivalId);
		} catch (HibernateException | NumberFormatException e) {
			response.sendRedirect("/error.html");
		}
		
	}
}

