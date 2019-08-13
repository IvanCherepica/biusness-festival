package servlets.mapUpdateServlets;

import models.Event;
import models.User;
import services.implementation.EventServiceImpl;
import services.implementation.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/userScheduleUpdate")
public class UserScheduleUpdateServlet extends HttpServlet {
    private EventServiceImpl eventService = EventServiceImpl.getInstance();

    private UserServiceImpl userService = UserServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean includeToSchedule = Boolean.parseBoolean(request.getParameter("includeToSchedule"));
        Long eventID = Long.parseLong(request.getParameter("eventID"));

        Event event = eventService.getById(eventID);


        if (includeToSchedule) {
            //user.addEvent(event); //
            eventService.addEventToUser(user.getId(),eventID);
        } else {
            eventService.addRemoveEventFromUser(user.getId(),eventID);
        }

        //userService.update(user);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Ok!");
    }
}
