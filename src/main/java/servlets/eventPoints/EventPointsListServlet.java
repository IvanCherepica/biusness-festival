package servlets.eventPoints;

import models.EventPoint;
import org.hibernate.HibernateException;
import services.abstraction.EventPoinService;
import services.implementation.EventPoinServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/eventpoints/list")
public class EventPointsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String paramId = request.getParameter("id");
        EventPoinService eventPoinService = EventPoinServiceImpl.getInstance();
    
        List<EventPoint> eventPoints = null;
    
        if (paramId == null || paramId.isEmpty()) {
            eventPoints = eventPoinService.getAllList();
        } else {
            try {
                long id = Long.parseLong(paramId);
                eventPoints = eventPoinService.getAllByFestival(id);
            } catch (HibernateException | NumberFormatException e) {
                response.sendRedirect("/error.html");
            }
        }
        request.setAttribute("EventPointsList", eventPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPoints/eventPointsList.jsp");
        dispatcher.forward(request, response);
    }
}
