package servlets;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UserServerDto;
import dto.UserSocketDto;
import models.Festival;
import services.FestivalService;
import services.FestivalServiceImpl;
import services.userNotificationServices.LocationWebSocketConfigurator;
import services.userNotificationServices.UserSessionService;
import util.UserJSONDataDeserializer;


import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

@ServerEndpoint(value = "/compareLocations", configurator= LocationWebSocketConfigurator.class)
public class LocationWebSocketServlet {

        private FestivalService festivalService = FestivalServiceImpl.getInstance();

        private String point;
        private Festival festival = new Festival("Test", "Testing", "Red" ,
            "Some", "60.11173060613703 30.267900556923905", 75);

         //список сессий
        private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

        //private FestivalService festivalService = FestivalServiceImpl.getInstance();


        @OnOpen
        public void start(Session userSession) {
            System.out.println("Connected user:" + userSession.getId());
            userSessions.add(userSession);
//            LocationWedSocketService.getInstance().setUserSessions(userSession);
        }

        @OnClose
        public void onClose(Session userSession) {
            userSessions.remove(userSession);
//            LocationWedSocketService.getInstance().removeUserSession(userSession);
            System.out.println("Disconnect user:" + userSession.getId());
        }

        @OnMessage
        public void onMessage(String message, Session userSession) throws Throwable {
            System.out.println("Message Received: " + message);
//            JSONObject obj = new JSONObject(message);
//            String userName = obj.getJSONObject("userName").getString("userName");

            //разбираем данные из JSON строки
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(UserServerDto.class, new UserJSONDataDeserializer());

            UserServerDto userServerDto = gsonBuilder.create().fromJson(message,UserServerDto.class);
            String userName = userServerDto.getUserName();
            Long userID = userServerDto.getId();
            point = userServerDto.getCoordinates();
            //find user http session
            UserSessionService userSessionService = UserSessionService.getInstance();
            HttpSession userHttpSession = userSessionService.getUserSession(userID);
            boolean isInFestivalOld = Boolean.parseBoolean((String) userHttpSession.getAttribute("userInFestival"));

            boolean isInFestivalNew = isInUnit(point, festival);

            UserSocketDto dto = new UserSocketDto();
            dto.setId(userID);
            dto.setName(userName);
            dto.setInFestival(isInFestivalNew);

            if (!isInFestivalOld && isInFestivalNew) {
                dto.setMessage("Welcome to JAVABOOTCAMP!");
            }

            try {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        userSession.getAsyncRemote().sendText(new Gson().toJson(dto));
                    }
                }, 10000);

                System.out.println(isInFestivalNew + " " + userSession.getId());
            } catch (Exception ex) {
                System.out.println("WebSocket session closed");
            }

            userHttpSession.setAttribute("userInFestival",Boolean.toString(isInFestivalNew));


//            sendRequestToUpdate(userSession);
//            LocationWedSocketService.getInstance().sendRequestToUpdate(message, userSession);
        }

        @OnError
        public void onError(Throwable t) throws Throwable {
            //TODO
        }

        // делаем запрос координат каждые 10 секунд
        private void sendRequestToUpdate(Session session) throws Throwable {
            boolean b = isInUnit(point, festival);
            try {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        session.getAsyncRemote().sendText("getCoordinates from" + session.getId());
                    }
                }, 10000);

                System.out.println(b + " " + session.getId());
            } catch (Exception ex) {
                System.out.println("WebSocket session closed");
            }
        }

        // проверяем, попал ли пользователь в зону фестиваля
        private boolean isInUnit(String point, Festival festival) {
            //FestivalService festivalService = FestivalServiceImpl.getInstance();
//            festivalService.add(new Festival("Test", "Testing", "Red" ,
//                    "Some", "60.11173060613703 30.267900556923905", 75));

            double[] pointCoordinates = getCoordinates(point);
            double[] festivalCoordinates = getCoordinates(festival.getCenter());
            double dx = pointCoordinates[0] - festivalCoordinates[0];
            double dy = pointCoordinates[1] - festivalCoordinates[1];
            double dx2 = Math.pow(dx, 2);
            double dy2 = Math.pow(dy, 2);
            double sum = dx2 + dy2;
            double sqrt = Math.sqrt(sum);
            double meters = sqrt*100000;
            return meters - festival.getRadius()<=0;
        }

        private double[] getCoordinates(String center) {
            double[] coordinates = new double[2];
            String[] XY = center.split(" ");

            coordinates[0] = Double.parseDouble(XY[0]);
            coordinates[1] = Double.parseDouble(XY[1]);
            return coordinates;
        }



}
