package dao;

import models.HotPoint;

public interface HotPointDAO<T> {
    T getById(long id);
    Long add(T hotPoint);
    void remove(long id);
    void update(HotPoint hotPoint);
    List<HotPoint> getAllList();
    List<HotPoint> getAllByFestival(Festival festival);
    void update(T hotPoint);
}
