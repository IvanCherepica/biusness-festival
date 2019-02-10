package servlets.eventPoints;

import services.EventPoinServiceImpl;

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

        List eventPoints = EventPoinServiceImpl.getInstance().getAllList();
        request.setAttribute("EventPointsList", eventPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPoints/eventPointsList.jsp");
        dispatcher.forward(request, response);
    }
}
