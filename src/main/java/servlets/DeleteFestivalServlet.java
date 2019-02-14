package servlets;

import org.hibernate.HibernateException;
import services.abstraction.FestivalService;
import services.implementation.FestivalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/deleteFestival")
public class DeleteFestivalServlet extends HttpServlet {
	private final FestivalService festivalService = FestivalServiceImpl.getInstance();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		String paramId = request.getParameter("festivalId");
		
		if (paramId == null) {
			response.sendRedirect("/error.html");
		} else {
			Long id = Long.parseLong(paramId);
			try {
				festivalService.remove(id);
			} catch (HibernateException e) {
				response.sendRedirect("/error.html");
			}
		}

		response.sendRedirect("/admin/festivals");
	}
	
}
