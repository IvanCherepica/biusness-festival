package services.abstraction;

import models.EventPoint;
import models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getById(long id);

    User getByName(String name);

    Long add(User user);

    void update(User user);

    void remove(long id);

    List<User> getAllList();
//    Set<EventPoint> getEventsFromUserId(long id);
//
//    void addEventsListToUserbyId(long id, Set<EventPoint> events);
//
//    void addEventToUserId(long id, EventPoint event);

}
