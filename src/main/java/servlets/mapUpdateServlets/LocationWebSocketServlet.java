package servlets.mapUpdateServlets;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UserServerDto;
import dto.UserSocketDto;
import models.Festival;
import services.abstraction.FestivalService;
import services.implementation.FestivalServiceImpl;
import services.userNotificationServices.LocationWebSocketConfigurator;
import services.userNotificationServices.UserSessionService;
import util.GeoDataHolder;
import util.UserJSONDataDeserializer;


import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.sql.SQLException;
import java.util.*;

@ServerEndpoint(value = "/compareLocations", configurator= LocationWebSocketConfigurator.class)
public class LocationWebSocketServlet {

        private GeoDataHolder geoDataHolder = GeoDataHolder.getGeoDataHolder();

        private FestivalService festivalService = FestivalServiceImpl.getInstance();


        private UserSessionService userSessionService = UserSessionService.getInstance();

        public LocationWebSocketServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        }

        @OnOpen
        public void start(Session userSession) {
            System.out.println("Connected user: " + userSession.getId());


            geoDataHolder.setCurrentFestivalId(0);
        }

        @OnClose
        public void onClose(Session userSession) {
            geoDataHolder.setCurrentFestivalId(0);


            System.out.println("Disconnect user:" + userSession.getId());
        }

        @OnMessage
        public void onMessage(String message, Session userSession)  {
            System.out.println("Message Received: " + message);

            //разбираем данные из JSON строки
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(UserServerDto.class, new UserJSONDataDeserializer());



            UserServerDto userServerDto = gsonBuilder.create().fromJson(message,UserServerDto.class);



            //String userName = userServerDto.getUserName();
            Long userID = userServerDto.getId();
            String point = userServerDto.getCoordinates();


            double[] userCoords = getCoordinates(point);
            geoDataHolder.setLatitude(userCoords[0]);
            geoDataHolder.setLongitude(userCoords[1]);

            //find user http session

            HttpSession userHttpSession = userSessionService.getUserSession(userID);
            boolean isInFestivalOld = Boolean.parseBoolean((String) userHttpSession.getAttribute("userInFestival"));

            Long currentFestivalID;

            boolean isInFestivalNew = false;
            Festival usersActivFestival = null;
            //at first check if user still in current Festival


            if (isInFestivalOld) {
                currentFestivalID = Long.parseLong((String) userHttpSession.getAttribute("currentFestivalID"));

                geoDataHolder.setCurrentFestivalId(currentFestivalID);

                Festival currentFestivale = festivalService.getById(currentFestivalID);
                isInFestivalNew =  isInUnit(point,currentFestivale);
                if (isInFestivalNew) {
                    usersActivFestival = currentFestivale;
                }
            }

            //Check for All active festivales
            if (!isInFestivalNew) {
                for (Festival currentFestivale : festivalService.getAllList()) {
                    isInFestivalNew = isInUnit(point, currentFestivale);
                    if (isInFestivalNew) {
                        userHttpSession.setAttribute("currentFestivalID",Long.toString(currentFestivale.getId()));
                        usersActivFestival = currentFestivale;
                        break;
                    }
                }
            }
//            boolean isInFestivalNew = isInUnit(point, festival);

            if (!isInFestivalOld && isInFestivalNew) {

                UserSocketDto dto = new UserSocketDto();
//                dto.setId(userID);
//                dto.setName(userName);
                dto.setInFestival(isInFestivalNew);
                dto.setFesivalId(usersActivFestival.getId());
                dto.setMessage("Wellcome to " + usersActivFestival.getName() + "! \n" + usersActivFestival.getDescription());
                userSession.getAsyncRemote().sendText(new Gson().toJson(dto));
            } else {
              //  userSession.getAsyncRemote().sendText("Responce from server isInFestival: " + isInFestivalNew);
            }

//            try {
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        userSession.getAsyncRemote().sendText(new Gson().toJson(dto));
//                    }
//                }, 10000);
//
//                System.out.println(isInFestivalNew + " " + userSession.getId());
//            } catch (Exception ex) {
//                System.out.println("WebSocket session closed");
//            }

            if (usersActivFestival == null) {
                userHttpSession.setAttribute("currentFestivalID",null);
            }
            userHttpSession.setAttribute("userInFestival",Boolean.toString(isInFestivalNew));


//            sendRequestToUpdate(userSession);
//            LocationWedSocketService.getInstance().sendRequestToUpdate(message, userSession);
        }

        @OnError
        public void onError(Throwable t) throws Throwable {
            t.printStackTrace();
            //TODO
        }


        // проверяем, попал ли пользователь в зону фестиваля
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

            System.out.println("distance=" + meters);

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
