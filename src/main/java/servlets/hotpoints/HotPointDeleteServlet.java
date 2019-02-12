package servlets.hotpoints;

import org.hibernate.HibernateException;
import services.HotPointService;
import services.HotPointServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/hotpoints/delete")
public class HotPointDeleteServlet extends HttpServlet {
    private final HotPointService hotPointService =  HotPointServiceImpl.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paramHotPontId = request.getParameter("hotPointId");
        String festivalId = request.getParameter("festivalId");
        
        if (paramHotPontId==null || paramHotPontId.isEmpty() || festivalId==null || festivalId.isEmpty()) {
            response.sendRedirect("/error.html");
        }
    
        try {
            long hPointId = Long.parseLong(paramHotPontId);
            hotPointService.remove(hPointId);
        } catch (HibernateException | NullPointerException e) {
            response.sendRedirect("/error.html");
        }
        response.sendRedirect("/admin/editFestival?festivalId="+festivalId);
    }
}