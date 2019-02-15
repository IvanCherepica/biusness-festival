package servlets;

import models.Event;
import models.EventPoint;
import models.Festival;
import models.HotPoint;
import org.hibernate.HibernateException;
import services.abstraction.EventPoinService;
import services.abstraction.EventService;
import services.abstraction.FestivalService;
import services.abstraction.HotPointService;
import services.implementation.EventPoinServiceImpl;
import services.implementation.EventServiceImpl;
import services.implementation.FestivalServiceImpl;
import services.implementation.HotPointServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/editFestival")
public class EditFestivalServlet extends HttpServlet {
	private final FestivalService festivalService =  FestivalServiceImpl.getInstance();
	private final EventPoinService eventPoinService =  EventPoinServiceImpl.getInstance();
	private final HotPointService hotPointService =  HotPointServiceImpl.getInstance();
	private final EventService eventService =  EventServiceImpl.getInstance();

	public EditFestivalServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/festivalEdit.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		String paramId = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String geometry = request.getParameter("geometry");
		String color = request.getParameter("color");
		String radius = request.getParameter("radius");
		String center = request.getParameter("center");
		
		try {
			long id = Long.parseLong(paramId);
			
			Festival festival = festivalService.getById(id);
			festival.setName(name == null ? "" : name);
			festival.setDescription(description == null ? "" : description);
			festival.setGeometry(geometry == null ? "" : geometry);
			festival.setColor(color == null ? "" : color);
			festival.setRadius(Double.valueOf(radius));
			festival.setCenter(center == null ? "" : center);
			
			festivalService.update(festival);

			response.sendRedirect("/admin/editFestival?festivalId="+paramId);
		} catch (HibernateException | NumberFormatException e) {
			System.out.println(e);
		}
	}
}

