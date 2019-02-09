package servlets;

import models.Festival;
import services.EventPoinServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/eventpoints")
public class EventPointsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List eventPoints = EventPoinServiceImpl.getInstance().getAllList();
        request.setAttribute("EventPointsList", eventPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventPointsList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString =request.getParameter("deleteId");
        System.out.println(idString);
        long id = Long.parseLong(idString);
        EventPoinServiceImpl.getInstance().remove(id);

        response.sendRedirect("/eventpoints");
    }
}
