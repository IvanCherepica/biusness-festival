package services.userNotificationServices;

import models.Festival;
import services.implementation.FestivalServiceImpl;

import javax.websocket.Session;
import java.util.*;

public class LocationWedSocketService {
    private static volatile LocationWedSocketService instance;
    private String point;
    private Festival festival;
    private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    private LocationWedSocketService() {
        this.festival = new Festival("Test", "Testing", "Red" ,
                "Some", "60.11173060613703 30.267900556923905", 75);
        this.point = "";

    }

    public static LocationWedSocketService getInstance() {
        if (instance == null) {
            synchronized (FestivalServiceImpl.class) {
                if (instance == null) {
                    instance = new LocationWedSocketService();
                }
            }
        }
        return instance;
    }

//    // делаем запрос координат каждые 10 секунд
//    public void sendRequestToUpdate(String message, Session session) throws Throwable {
//        try {
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    session.getAsyncRemote().sendText("getCoordinates from" + session.getId());
//                }
//            }, 10000);
//            boolean b = isInUnit(point, festival);
//            System.out.println(b + " " + session.getId());
//        } catch (IllegalStateException ex) {
//            System.out.println("WebSocket session closed");
//        }
//    }
//
//    // проверяем, попал ли пользователь в зону фестиваля
//    private boolean isInUnit(String point, Festival festival) {
//        double[] pointCoordinates = getCoordinates(point);
//        double[] festivalCoordinates = getCoordinates(festival.getCenter());
//        double dx = pointCoordinates[0] - festivalCoordinates[0];
//        double dy = pointCoordinates[1] - festivalCoordinates[1];
//        double dx2 = Math.pow(dx, 2);
//        double dy2 = Math.pow(dy, 2);
//        double sum = dx2 + dy2;
//        double sqrt = Math.sqrt(sum);
//        double meters = sqrt*100000;
//        return meters - festival.getRadius()<=0;
//    }
//
//    private double[] getCoordinates(String center) {
//        double[] coordinates = new double[2];
//        String[] XY = center.split(" ");
//
//        coordinates[0] = Double.parseDouble(XY[0]);
//        coordinates[1] = Double.parseDouble(XY[1]);
//        return coordinates;
//    }
//
//    public void setUserSessions (Session userSession){
//        this.userSessions.add(userSession);
//    }
//
//    public void removeUserSession(Session userSession) {
//        this.userSessions.remove(userSession);
//    }
}
