package src.main.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/geometry")
public class GeometryServlet extends HttpServlet {

    // Геометрическая фигура фестиваля - массив координат в формате JSON
    private static final String geometryJSON= "[\n" +
            "                [60.112539461325454, 30.266258256820947], [60.11261430744659, 30.267031399988582],\n" +
            "                [60.112539461325454, 30.267707316660335], [60.112443230348305, 30.268233029627254],\n" +
            "                [60.11230737114067, 30.268683640741756], [60.11210956132176, 30.26911279418414],\n" +
            "                [60.111778093556026, 30.26960632064288], [60.11153216370285, 30.269906728052547],\n" +
            "                [60.11107772327217, 30.269992558741023], [60.11104029848663, 30.269413201593807],\n" +
            "                [60.11105633768563, 30.268629996561458], [60.11102960568295, 30.268018452906063],\n" +
            "                [60.11102425927979, 30.267782418512752], [60.11129692473338, 30.267685858988216],\n" +
            "                [60.11145731512209, 30.267514197611263], [60.11146800778689, 30.26711723067706],\n" +
            "                [60.111476027283246, 30.266645161890438], [60.11155354898036, 30.266462771677425],\n" +
            "                [60.11177274727439, 30.266484229349544], [60.11192511596062, 30.266468136095455],\n" +
            "                [60.1121362924478, 30.266414491915157], [60.112331429010574, 30.26632866122668],\n" +
            "                [60.112481122028406, 30.266216008448055], [60.112539461325454, 30.266258256820947]\n" +
            "            ]";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(geometryJSON);
    }
}
