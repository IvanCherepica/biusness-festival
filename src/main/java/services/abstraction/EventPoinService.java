package services.abstraction;

import models.EventPoint;
import models.Festival;
import models.User;

import java.util.List;

public interface EventPoinService {

    EventPoint getById(long id);
    EventPoint getByName(String name);
    Long add(EventPoint festival);
    void update(EventPoint eventPoint);
    void remove(long id);
    List<EventPoint> getAllList();
	List<EventPoint> getAllByFestival(long id);
	List<EventPoint> getAllEventPointByFestivalId(long festivalId);
//	void addUsersToEventId(long id, List<User> users);
//	void addUserToEventId(long id, User user);
}
