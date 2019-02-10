package servlets;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/compareLocations")
public class LocationWebSocketServlet {

        private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

        @OnOpen
        public void start(Session userSession) {
            userSessions.add(userSession);
        }

        @OnClose
        public void onClose(Session userSession) {
            userSessions.remove(userSession);
        }

        @OnMessage
        public void onMessage(String message, Session userSession) {
            System.out.println("Message Received: " + message);
            for (Session session : userSessions) {
                System.out.println("Sending to " + session.getId());
                session.getAsyncRemote().sendText(message);
            }
        }

        @OnError
        public void onError(Throwable t) throws Throwable {
            //TODO
        }


        public void sendRequestToUpdate(Throwable t) throws Throwable {

        }



}
