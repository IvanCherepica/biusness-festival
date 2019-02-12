package servlets;

import com.google.gson.Gson;
import dto.UserSocketDto;
import models.Festival;
import models.User;
import services.FestivalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/rest/info")
public class MainPageUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean userInFestival = Boolean.parseBoolean((String)session.getAttribute("userInFestival"));
        Festival festival = null;
        if(userInFestival){
            FestivalServiceImpl festivalService = FestivalServiceImpl.getInstance();
            festival = festivalService.getById((long)session.getAttribute("currentFestivalID"));
        }
        UserSocketDto dto = new UserSocketDto();
        dto.setUser(user);
        dto.setInFestival(userInFestival);
        dto.setFestival(festival);
        String json = new Gson().toJson(dto);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
