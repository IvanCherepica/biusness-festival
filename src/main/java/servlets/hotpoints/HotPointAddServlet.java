package servlets.hotpoints;


import models.HotPoint;
import models.Festival;
import services.implementation.FestivalServiceImpl;
import services.implementation.HotPointServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/hotpoints/addhot")
public class HotPointAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long festivalId = 1;
        request.setAttribute("festivalId", festivalId);

        List<Festival> festivals = FestivalServiceImpl.getInstance().getAllList();

        request.setAttribute("festivals", festivals);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotpoints/hotpointadd.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String hName = request.getParameter("name");
        String hDescription = request.getParameter("description");
        String hGeometry = request.getParameter("geometry");
        String hColor = request.getParameter("color");

        long festId = Long.parseLong(request.getParameter("festivalId"));

        Festival fest = FestivalServiceImpl.getInstance().getById(festId);

        HotPoint hpoint = new HotPoint(hName,hDescription,hGeometry,hColor,fest,"1",1);

        HotPointServiceImpl.getInstance().add(hpoint);

        //response.sendRedirect("/admin/editFestival?festivalId="+festId);
    }
}
