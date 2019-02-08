package dao;

import models.HotPoint;

public interface HotPointDAO<T> {
    T getById(long id);
    Long add(T hotPoint);
    void remove(long id);
    void update(T hotPoint);
}
