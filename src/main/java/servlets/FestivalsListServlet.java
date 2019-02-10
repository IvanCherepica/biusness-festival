package servlets;

import models.Festival;
import services.FestivalService;
import services.FestivalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/festivals")
public class FestivalsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FestivalService festivalService = FestivalServiceImpl.getInstance();
        List<Festival> festivals = festivalService.getAllList();
        request.setAttribute("FestivalsList", festivals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/festivalsList.jsp");
        dispatcher.forward(request, response);


    }
}
