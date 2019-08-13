package servlets;

import models.Festival;
import services.abstraction.FestivalService;
import services.implementation.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/addFest")
public class AddFestivalServlet extends HttpServlet {
    private final FestivalService festivalService=  FestivalServiceImpl.getInstance();

	public AddFestivalServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;UTF-8");
		String paramId = request.getParameter("edit");
    	Festival festival;
    	
	    if (paramId==null) {
		    festival = new Festival("Name", "Description", "Coordinates", "Color", "1", 1);
	    } else {
		    Long id = Long.parseLong(paramId);
		    festival = festivalService.getById(id);
	    }
		request.setAttribute("festival", festival);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/festivalAdd.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String geometry = request.getParameter("geometry");
	    String color = request.getParameter("color");
		
	    if (name==null || name.isEmpty()) {
		    response.sendRedirect("/error.html");
	    }
        Festival festival = new Festival(name, description, geometry, color, "1", 1);
	    
        festivalService.add(festival);


        response.sendRedirect("/admin/festivals");
    }
}