package servlets;

import models.Festival;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/festivals")
public class FestivalsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List festivals = new ArrayList<Festival>();


        Festival f1 = new Festival();
        f1.setId(54);
        f1.setColor("c1");
        f1.setDescription("d1");
        f1.setGeometry("g1");
        f1.setName("n1");


        Festival f2 = new Festival();
        f2.setId(54);
        f2.setColor("c2");
        f2.setDescription("d21");
        f2.setGeometry("g2");
        f2.setName("n2");

        festivals.add(f1);
        festivals.add(f2);



        request.setAttribute("FestivalsList", festivals);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/festivalsList.jsp");
        dispatcher.forward(request, response);







    }
}
