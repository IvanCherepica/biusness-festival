package dao;

import models.Festival;
import models.HotPoint;

import java.util.List;

public interface HotPointDAO<T> {
    T getById(long id);
    Long add(T hotPoint);
    void remove(long id);
    List<T> getAllList();
    List<T> getAllByFestival(Festival festival);
    void update(T hotPoint);
}
