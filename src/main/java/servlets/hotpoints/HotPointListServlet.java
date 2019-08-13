package servlets.hotpoints;

import models.HotPoint;
import org.hibernate.HibernateException;
import services.abstraction.HotPointService;
import services.implementation.HotPointServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/hotpoints/list")
public class HotPointListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

        String paramId = request.getParameter("id");
        HotPointService hotPoinService = HotPointServiceImpl.getInstance();
    
        List<HotPoint> hotPoints = null;
        
        if (paramId == null || paramId.isEmpty()) {
            hotPoints = hotPoinService.getAllList();
        } else {
            try {
                long id = Long.parseLong(paramId);
                hotPoints = hotPoinService.getAllByFestival(id);
            } catch (HibernateException | NumberFormatException e) {
                response.sendRedirect("/error.html");
            }
        }
        request.setAttribute("HotPointList", hotPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotpoints/hotpointlist.jsp");
        dispatcher.include(request, response);
    }
}



