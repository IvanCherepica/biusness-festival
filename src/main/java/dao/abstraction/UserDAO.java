package dao.abstraction;

import models.Event;
import models.User;

import java.util.List;

public interface UserDAO {
    User getById(long id);

    User getByName(String name);

    Long add(User user);

    void remove(long id);

    void update(User user);

    List<User> getAllList();

    List<Event> getUserSchedule(long userId);
}