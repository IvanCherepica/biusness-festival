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


@WebServlet("/admin/eventpoints/edit")
public class EventPointEdit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        List<Festival> festivals = null;

        festivals = FestivalServiceImpl.getInstance().getAllList();

        request.setAttribute("festivals", festivals);


        long eventPointId = Long.parseLong(request.getParameter("eventPointId"));
        EventPoint eventPoint = null;

        eventPoint = EventPoinServiceImpl.getInstance().getById(eventPointId);

        request.setAttribute("eventPoint", eventPoint);
        //request.setAttribute("festivalId", eventPoint.getFestival().getId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPoints/eventPointEdit.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        long eventPointId = Long.parseLong(request.getParameter("eventPointId"));
        long festivalId = Long.parseLong(request.getParameter("festivalId"));

        EventPoint eventPoint = null;

        eventPoint = EventPoinServiceImpl.getInstance().getById(eventPointId);

        Festival festival = null;

        festival = FestivalServiceImpl.getInstance().getById(festivalId);


        String eventName = request.getParameter("name");
        String eventDescription = request.getParameter("description");
        String eventGeometry = request.getParameter("geometry");
        String eventColor = request.getParameter("color");

        eventPoint.setName(eventName);
        eventPoint.setColor(eventColor);
        eventPoint.setDescription(eventDescription);
        eventPoint.setFestival(festival);
        eventPoint.setGeometry(eventGeometry);

         EventPoinServiceImpl.getInstance().update(eventPoint);

        //response.sendRedirect("/admin/editFestival?festivalId="+festivalId);
        
        }
    }