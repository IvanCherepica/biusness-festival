package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.FestivalDataDto;
import dto.UserScheduleDto;
import models.Event;
import models.EventPoint;
import models.Festival;
import services.implementation.UserServiceImpl;
import util.FestivalDataToMapSerealializer;
import util.UserScheduleDtoSerealializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/get_user_schedule")
public class GetUserScheduleServlet extends HttpServlet {
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long userId = Long.parseLong(request.getParameter("userId"));

        List<Event> eventList = userService.getUserSchedule(userId);

        UserScheduleDto userScheduleDto = new UserScheduleDto();
        userScheduleDto.setEventsList(eventList);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(UserScheduleDto.class, new UserScheduleDtoSerealializer())
                .create();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(userScheduleDto));

    }
}
