package services.userNotificationServices;

import servlets.userNotificationServlets.LocationWebSocketServlet;

import javax.websocket.server.ServerEndpointConfig.Configurator;

public class LocationWebSocketConfigurator extends Configurator {
        private static LocationWebSocketServlet locationWebSocketServlet = new LocationWebSocketServlet();

        @Override
        public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
            return (T)locationWebSocketServlet;
        }
}