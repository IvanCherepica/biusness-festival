package servlets;

import models.Event;
import models.User;
import org.hibernate.HibernateException;
import services.abstraction.EventService;
import services.abstraction.UserService;
import services.implementation.EventServiceImpl;
import services.implementation.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();
    private final EventService eventService= EventServiceImpl.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String paramId = request.getParameter("edit");
        User user;


        if (paramId == null) {
            response.sendRedirect("/error.html");
        } else {
            long id = Long.parseLong(paramId);
            user = userService.getById(id);
            Set<Event> eventFromUser = user.getEvents();
            List<Event> allEventsFromDB=eventService.getAllList();
            request.setAttribute("user", user);
            boolean p= allEventsFromDB.removeAll(eventFromUser);
            request.setAttribute("eventsp",allEventsFromDB);
            request.setAttribute( "ueventsp",eventFromUser);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/userEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

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
            Set<Event> userEvents= user.getEvents();
            //добавление ивентов для участника
            if (userEvents != null) {
               if (eventId != null) {
                   Set<Event> events = new HashSet<>();
                   for (String eve : eventId) {
                       events.add(eventService.getById(Long.parseLong(eve)));
                   }
//                   user.setEventsFromFest(events);
               }
               else {
                   userEvents.removeAll(userEvents);
//                   user.setEventsFromFest(userEvents);
               }
            }

            //конец добавления
            userService.update(user);

            response.sendRedirect("/admin/users");
        } catch (HibernateException | NumberFormatException e) {
            response.sendRedirect("/error.html");
        }
    }
}
