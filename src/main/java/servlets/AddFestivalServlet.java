package servlets;

import models.Festival;
import services.FestivalService;
import services.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/addFest")
public class AddFestivalServlet extends HttpServlet {
    private final FestivalService festivalService=  FestivalServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
		String paramId = request.getParameter("edit");
    	Festival festival;
    	
	    if (paramId==null) {
		    festival = new Festival("Name", "Description", "Coordinates", "Color", "1", 1);
	    } else {
		    Long id = Long.parseLong(paramId);
		    festival = festivalService.getById(id);
	    }
		request.setAttribute("festival", festival);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/addFest.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String geometry = request.getParameter("geometry");
	    String color = request.getParameter("color");
		
	    if (name==null || name.isEmpty()) {
		    response.sendRedirect("/error.html");
	    }
        Festival festival = new Festival(name, description, geometry, color, "1", 1);
	    
        festivalService.add(festival);

        response.setContentType("text/html");
        response.sendRedirect("/admin/festivals");
    }
}