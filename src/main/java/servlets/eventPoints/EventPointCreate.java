package servlets.eventPoints;


import models.EventPoint;
import models.Festival;
import services.implementation.EventPoinServiceImpl;
import services.implementation.FestivalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/eventpoints/create")
public class EventPointCreate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        long festivalId = 1;
        request.setAttribute("festivalId", festivalId);

        List<Festival> festivals = null;

        festivals = FestivalServiceImpl.getInstance().getAllList();

        request.setAttribute("festivals", festivals);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPoints/EventPointCreate.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String eventName = request.getParameter("name");
        String eventDescription = request.getParameter("description");
        String eventGeometry = request.getParameter("geometry");
        String eventColor = request.getParameter("color");

        long festivalId = Long.parseLong(request.getParameter("festivalId"));

        Festival festival = null;

        festival = FestivalServiceImpl.getInstance().getById(festivalId);


        EventPoint eventPoint = new EventPoint();
        eventPoint.setName(eventName);
        eventPoint.setColor(eventColor);
        eventPoint.setDescription(eventDescription);
        eventPoint.setFestival(festival);
        eventPoint.setGeometry(eventGeometry);

        EventPoinServiceImpl.getInstance().add(eventPoint);

       // response.sendRedirect("/admin/editFestival?festivalId="+festivalId);
    }
}
