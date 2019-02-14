package servlets;

import models.Festival;
import services.implementation.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/festivals")
public class FestivalsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        List<Festival> festivals = FestivalServiceImpl.getInstance().getAllList();
        request.setAttribute("FestivalsList", festivals);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/festivalsList.jsp");
        dispatcher.forward(request, response);
    }
}
