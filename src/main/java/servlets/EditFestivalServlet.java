package servlets;

import models.Event;
import models.EventPoint;
import models.Festival;
import models.HotPoint;
import org.hibernate.HibernateException;
import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/editFestival")
public class EditFestivalServlet extends HttpServlet {
	private final FestivalService festivalService =  FestivalServiceImpl.getInstance();
	private final EventPoinService eventPoinService =  EventPoinServiceImpl.getInstance();
	private final HotPointService hotPointService =  HotPointServiceImpl.getInstance();
	private final EventService eventService =  EventServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String paramId = request.getParameter("festivalId");
		Festival festival;
		
		if (paramId==null) {
			response.sendRedirect("/error.html");
		} else {
			long id = Long.parseLong(paramId);
			festival = festivalService.getById(id);
			List<EventPoint> eventPoints = eventPoinService.getAllByFestival(id);
			List<HotPoint> hotPoints = hotPointService.getAllByFestival(id);
			List<Event> events = eventService.getAllByFestival(id);
			
			request.setAttribute("festival", festival);
			//request.setAttribute("eventPointsList", eventPoints);
			request.setAttribute("eventPointsList", eventPoints);
			request.setAttribute("hotPointList", hotPoints);
			request.setAttribute("eventList", events);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editFestival.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String geometry = request.getParameter("geometry");
		String color = request.getParameter("color");
		String radius = request.getParameter("radius");
		String center = request.getParameter("center");
		
		try {
			long id = Long.parseLong(paramId);
			long r = Long.parseLong(radius);
			
			Festival festival = festivalService.getById(id);
			festival.setName(name == null ? "" : name);
			festival.setDescription(description == null ? "" : description);
			festival.setGeometry(geometry == null ? "" : geometry);
			festival.setColor(color == null ? "" : color);
			festival.setRadius(Long.valueOf(radius));
			festival.setCenter(center == null ? "" : center);
			
			festivalService.update(festival);
			
			response.setContentType("text/html");
			response.sendRedirect("/admin/editFestival?festivalId="+paramId);
		} catch (HibernateException | NumberFormatException e) {
			response.sendRedirect("/error.html");
		}
	}
}

