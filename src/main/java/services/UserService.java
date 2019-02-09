package services;


import models.HotPoint;
import models.User;

public interface UserService {
    User getById(long id);
    User getByName(String name);
    Long add(User user);
    void update(User user);
    void remove(long id);

}
