package servlets.events;

import org.hibernate.HibernateException;
import services.abstraction.EventService;
import services.implementation.EventServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/events/delete")
public class DeleteEventServlet extends HttpServlet {
	private final EventService eventService = EventServiceImpl.getInstance();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		String paramId = request.getParameter("eventId");
		String festivalId = request.getParameter("festivalId");
		
		if (paramId == null) {
			response.sendRedirect("/error.html");
		} else {
			Long id = Long.parseLong(paramId);
			try {
				eventService.remove(id);
			} catch (HibernateException e) {
				response.sendRedirect("/error.html");
			}
		}

		response.sendRedirect("/admin/editfestival?id="+festivalId);
	}
	
}
