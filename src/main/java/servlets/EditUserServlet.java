package servlets;

import models.EventPoint;
import models.User;
import org.hibernate.HibernateException;
import services.EventPoinService;
import services.EventPoinServiceImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();
    private final EventPoinService eventPoinService= EventPoinServiceImpl.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String paramId = request.getParameter("edit");
        User user;


        if (paramId == null) {
            response.sendRedirect("/error.html");
        } else {
            long id = Long.parseLong(paramId);
            user = userService.getById(id);
            List<EventPoint> eventFromUser = user.getEvents();
            List<EventPoint> allEventsFromDB=eventPoinService.getAllList();
            request.setAttribute("user", user);
            boolean p= allEventsFromDB.removeAll(eventFromUser);
            request.setAttribute("eventsp",allEventsFromDB);
            request.setAttribute( "ueventsp",eventFromUser);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paramId = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String[] eventId= request.getParameterValues("epoint");

        try {
            Long id = Long.parseLong(paramId);
            User user = userService.getById(id);
            user.setName(name == null ? "" : name);
            user.setPassword(password == null ? "" : password);
            user.setRole(role == null ? "" : role);
            List<EventPoint> userEvents= user.getEvents();
            //добавление ивентов для участника
            if (userEvents != null) {
               if (eventId != null) {
                   List<EventPoint> events = new ArrayList<>();
                   for (String eve : eventId) {
                       events.add(eventPoinService.getById(Long.parseLong(eve)));
                   }
                   user.setEventsToUser(events);
               }
               else {
                   userEvents.removeAll(userEvents);
                   user.setEventsToUser(userEvents);
               }
            }

            //конец добавления
            userService.update(user);

            response.setContentType("text/html");
            response.sendRedirect("/admin/users");
        } catch (HibernateException | NumberFormatException e) {
            response.sendRedirect("/error.html");
        }
    }
}
