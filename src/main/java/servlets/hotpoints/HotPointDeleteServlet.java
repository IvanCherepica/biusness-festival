package servlets.hotpoints;

import services.HotPointServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/hotpoints/delete")
public class HotPointDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        long hPointId = Long.parseLong(request.getParameter("hPointId"));
        HotPointServiceImpl.getInstance().remove(hPointId);

        response.sendRedirect("/admin/hotpoints/list");
    }
}