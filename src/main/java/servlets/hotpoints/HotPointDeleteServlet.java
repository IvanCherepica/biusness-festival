package servlets.hotpoints;

import org.hibernate.HibernateException;
import services.abstraction.HotPointService;
import services.implementation.HotPointServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/hotpoints/delete")
public class HotPointDeleteServlet extends HttpServlet {
    private final HotPointService hotPointService =  HotPointServiceImpl.getInstance();

    public HotPointDeleteServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");

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