package servlets;

import com.google.gson.Gson;
import models.Festival;
import services.FestivalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rest/geometry")
public class GeometryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Festival> festivals= FestivalServiceImpl.getInstance().getAllList();

        Gson gson = new Gson();
        String message = gson.toJson(festivals);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
    }
}
