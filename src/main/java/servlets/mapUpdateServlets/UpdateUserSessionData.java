package servlets.mapUpdateServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UserSocketDto;
import models.User;
import util.UserSocketSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/rest/userdata")
public class UpdateUserSessionData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userInFestival = (String) session.getAttribute("userInFestival");

        UserSocketDto dto = new UserSocketDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUser(user);
        dto.setInFestival(Boolean.parseBoolean(userInFestival));

        //String json = new Gson().toJson(dto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(UserSocketDto.class, new UserSocketSerializer())
                .create();

        response.setContentType("application/json");
        //response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(dto));
    }
}
