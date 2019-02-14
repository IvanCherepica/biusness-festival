package servlets.hotpoints;

import models.HotPoint;
import models.Festival;
import services.implementation.HotPointServiceImpl;
import services.implementation.FestivalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/hotpoints/edit")
public class HotPointEditorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        List<Festival> festivals = FestivalServiceImpl.getInstance().getAllList();
        request.setAttribute("festivals", festivals);


        long hPointId = Long.parseLong(request.getParameter("hPointId"));
        HotPoint hPoint = HotPointServiceImpl.getInstance().getById(hPointId);
        request.setAttribute("hPoint", hPoint);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotpoints/hotpointedit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        long hPointId = Long.parseLong(request.getParameter("hotPointId"));
        long fId = Long.parseLong(request.getParameter("festivalId"));

        HotPoint hPoint = HotPointServiceImpl.getInstance().getById(hPointId);
        Festival festival = FestivalServiceImpl.getInstance().getById(fId);

        String Name = request.getParameter("name");
        String Description = request.getParameter("description");
        String Geometry = request.getParameter("geometry");
        String Color = request.getParameter("color");

        hPoint.setName(Name);
        hPoint.setColor(Color);
        hPoint.setDescription(Description);
        hPoint.setFestival(festival);
        hPoint.setGeometry(Geometry);

        HotPointServiceImpl.getInstance().update(hPoint);

        //response.sendRedirect("/admin/editFestival");

        //response.sendRedirect("/admin/editFestival");
    }

}
