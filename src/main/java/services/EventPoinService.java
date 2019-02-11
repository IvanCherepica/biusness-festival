package services;

import models.EventPoint;
import models.Festival;

import java.util.List;

public interface EventPoinService {

    EventPoint getById(long id);
    EventPoint getByName(String name);
    Long add(EventPoint festival);
    void update(EventPoint eventPoint);
    void remove(long id);
    List<EventPoint> getAllList();
    List<EventPoint> getAllEventPointByFestivalId(long festivalId);
}
