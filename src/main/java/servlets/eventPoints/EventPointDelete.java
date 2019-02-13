package servlets.eventPoints;

import org.hibernate.HibernateException;
import services.EventPoinService;
import services.EventPoinServiceImpl;
import services.FestivalService;
import services.FestivalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/eventpoints/delete")
public class EventPointDelete extends HttpServlet {
    private final FestivalService festivalService = FestivalServiceImpl.getInstance();
    private final EventPoinService eventPoinService =  EventPoinServiceImpl.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String paramEventPontId = request.getParameter("eventPointId");
        String festivalId = request.getParameter("festivalId");
        if (paramEventPontId==null || paramEventPontId.isEmpty() || festivalId==null || festivalId.isEmpty()) {
            response.sendRedirect("/error.html");
        }
        
        try {
            long eventPointId = Long.parseLong(paramEventPontId);
            eventPoinService.remove(eventPointId);
        } catch (HibernateException | NullPointerException e) {
            response.sendRedirect("/error.html");
        }
        response.sendRedirect("/admin/editFestival?festivalId="+festivalId);
    }
}
