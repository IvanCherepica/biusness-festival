package services.userNotificationServices;

import servlets.mapUpdateServlets.LocationWebSocketServlet;

import javax.websocket.server.ServerEndpointConfig.Configurator;
import java.sql.SQLException;

public class LocationWebSocketConfigurator extends Configurator {
        private static LocationWebSocketServlet locationWebSocketServlet;

    static {
        try {
            locationWebSocketServlet = new LocationWebSocketServlet();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
        public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
            return (T)locationWebSocketServlet;
        }
}