package dao;

import models.EventPoint;

import java.util.List;

public interface EventPointDAO {
    EventPoint getById(long id);

    Long add(EventPoint eventPoint);

    EventPoint getByName(String name);

    void remove(long id);

    void update(EventPoint eventPoint);

    List<EventPoint> getAllEventPointByFestivalId(long festivalId);

    List<EventPoint> getAllList();

}
