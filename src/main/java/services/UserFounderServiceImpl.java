package services;


import models.Festival;

import javax.servlet.http.HttpSession;

public class UserFounderServiceImpl implements UserFounderService {
    HttpSession session;

    public UserFounderServiceImpl(HttpSession session){
        this.session = session;
    }

    public void isUserInFestival(String point, Festival festival){
        session.setAttribute("isInUnit", isInUnit(point, festival));
    }

    private boolean isInUnit(String point, Festival festival) {
        double[] pointCoordinates = getCoordinates(point);
        double[] festivalCoordinates = getCoordinates(festival.getCenter());
        double dx = pointCoordinates[0] - festivalCoordinates[0];
        double dy = pointCoordinates[1] - festivalCoordinates[1];
        double dx2 = Math.pow(dx, 2);
        double dy2 = Math.pow(dy, 2);
        double sum = dx2 + dy2;
        double sqrt = Math.sqrt(sum);
        double meters = sqrt*100000;
        boolean b =  meters - festival.getRadius()<=0;
        return b;
        //return (Math.sqrt((Math.pow(pointCoordinates[0] - festivalCoordinates[0], 2)) +
        //        (Math.pow(pointCoordinates[1] - festivalCoordinates[1], 2)))-festival.getRadius())<0.00;
    }

    private double[] getCoordinates(String center) {
        double[] coordinates = new double[2];
        String[] XY = center.split(" ");
        coordinates[0] = Double.parseDouble(XY[0]);
        coordinates[1] = Double.parseDouble(XY[1]);
        return coordinates;
    }
}
