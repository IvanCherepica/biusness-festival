package dao;

import models.HotPoint;

public interface HotPointDAO {
    HotPoint getById(long id);
    Long save(HotPoint hotPoint);
    void remove(long id);
    void update(HotPoint hotPoint);
}
