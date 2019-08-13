package dao.abstraction;

import models.EventPoint;
import java.util.List;

public interface EventPointDAO {
    EventPoint getById(long id);
    Long add(EventPoint eventPoint);
    EventPoint getByName(String name);
    void remove(long id);
    void update(EventPoint eventPoint);
    public void clearCash ();

    List<EventPoint> getAllList();
    List<EventPoint> getAllByFestival (long id);
    List<EventPoint> getAllEventPointByFestivalId(long festivalId);
}
