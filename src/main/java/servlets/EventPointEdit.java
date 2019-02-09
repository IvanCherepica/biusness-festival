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
            eventPoint = new EventPoint("name", "des", "geometry", "color", festival);
        }

        response.setContentType("text/html");

        request.setAttribute("EventPoint", eventPoint);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPointEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("name");
//        String description = request.getParameter("description");
//        String geomertyJson = request.getParameter("geometry");
//
//        Festival fest = new Festival(login, description, geomertyJson, "black");
//        festivalad.add(fest);
//
//
//        response.setContentType("text/html");
//        response.sendRedirect("/festivals");
    }
}