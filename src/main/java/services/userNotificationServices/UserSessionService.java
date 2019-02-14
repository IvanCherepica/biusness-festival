package services.userNotificationServices;

import services.implementation.FestivalServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.*;

public class UserSessionService {
    private static volatile UserSessionService instance;

    private Set<HttpSession> userSessions = Collections.synchronizedSet(new HashSet<>());
    private Map<Long,HttpSession> userSessionsMap = Collections.synchronizedMap(new HashMap<>());
    private UserSessionService() {
    }

    public static UserSessionService getInstance() {
        if (instance == null) {
            synchronized (FestivalServiceImpl.class) {
                if (instance == null) {
                    instance = new UserSessionService();
                }
            }
        }
        return instance;
    }

    public void addUserSessions (Long userID, HttpSession userSession){
        this.userSessions.add(userSession);
        this.userSessionsMap.put(userID,userSession);
    }

    public HttpSession getUserSession (Long userID) {
        return this.userSessionsMap.get(userID);
    }

    public void removeUserSession(HttpSession userSession) {
        this.userSessions.remove(userSession);

    }
}
