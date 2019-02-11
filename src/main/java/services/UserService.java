package services;

import models.EventPoint;
import models.User;

import java.util.List;

public interface UserService {
    User getById(long id);

    User getByName(String name);

    Long add(User user);

    void update(User user);

    void remove(long id);

    List<User> getAllList();
}
