package servlets;
        import models.EventPoint;
        import models.Festival;
        import services.EventPoinServiceImpl;
        import services.FestivalService;
        import services.FestivalServiceImpl;
        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
                import java.io.IOException;


@WebServlet("/eventpointedit")
public class EventPointEdit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idString =request.getParameter("editId");
        System.out.println(idString);

        long festivalId = 1;
        Festival festival = FestivalServiceImpl.getInstance().getById(festivalId);

        long eventPointId = -1;
        EventPoint eventPoint = null;

        try {
            eventPointId = Long.parseLong(idString);
            eventPoint = EventPoinServiceImpl.getInstance().getById(eventPointId);
        }
        catch (NumberFormatException e){
            eventPointId = -1;
            eventPoint = new EventPoint("nameNew", "desNew", "geometryNew", "colorNew", festival, "60 40" , 50);
            eventPoint.setId(eventPointId);
        }

        response.setContentType("text/html");

        request.setAttribute("eventPoint", eventPoint);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPointEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long eventPointId = Long.parseLong(request.getParameter("eventPointId"));
        String eventName = request.getParameter("eventName");
        String eventDescription = request.getParameter("description");
        String eventGeometry = request.getParameter("geometry");
        String eventColor = request.getParameter("color");

        long festivalId = Long.parseLong(request.getParameter("festivalId"));
        Festival festival = FestivalServiceImpl.getInstance().getById(festivalId);

        EventPoint eventPoint = EventPoinServiceImpl.getInstance().getById(eventPointId);

        if (eventPoint == null) {
            eventPoint = new EventPoint();
            updateEventPoint(eventName, eventDescription, eventGeometry, eventColor, festival, eventPoint);
            EventPoinServiceImpl.getInstance().add(eventPoint);
        }
        else{
            System.out.println(eventPoint.toString());
            updateEventPoint(eventName, eventDescription, eventGeometry, eventColor, festival, eventPoint);
            EventPoinServiceImpl.getInstance().update(eventPoint);
        }

        System.out.println(eventPoint.toString());



    }

    private void updateEventPoint(String eventName, String eventDescription, String eventGeometry, String eventColor, Festival festival, EventPoint eventPoint) {
        eventPoint.setName(eventName);
        eventPoint.setColor(eventColor);
        eventPoint.setDescription(eventDescription);
        eventPoint.setFestival(festival);
        eventPoint.setGeometry(eventGeometry);

    }
}