package servlets;

import models.Festival;
import org.hibernate.HibernateException;
import services.FestivalService;
import services.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editFestival")
public class EditFestivalServlet extends HttpServlet {
	private final FestivalService festivalService =  FestivalServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String paramId = request.getParameter("edit");
		Festival festival;
		
		if (paramId==null) {
			response.sendRedirect("/error.html");
		} else {
			long id = Long.parseLong(paramId);
			festival = festivalService.getById(id);
			request.setAttribute("festival", festival);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("editFestival.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String geometry = request.getParameter("geometry");
		String color = request.getParameter("color");
		
		try {
			Long id = Long.parseLong(paramId);
			Festival festival = festivalService.getById(id);
			festival.setName(name == null ? "" : name);
			festival.setDescription(description == null ? "" : description);
			festival.setGeometry(geometry == null ? "" : geometry);
			festival.setColor(color == null ? "" : color);
			
			festivalService.update(festival);
			
			response.setContentType("text/html");
			response.sendRedirect("/admin/festivals");
		} catch (HibernateException | NumberFormatException e) {
			response.sendRedirect("/error.html");
		}
	}
}

