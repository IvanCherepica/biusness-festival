package dao;

import models.EventPoint;

public interface EventPointDAO {
    EventPoint getById(long id);
    Long add(EventPoint eventPoint);
    void remove(long id);
    void update(EventPoint eventPoint);
}
