package servlets.eventPoints;

import services.EventPoinServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/eventpoints/delete")
public class EventPointDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        long eventPointId = Long.parseLong(request.getParameter("eventPointId"));
        EventPoinServiceImpl.getInstance().remove(eventPointId);

        response.sendRedirect("/admin/eventpoints/list");
    }
}
