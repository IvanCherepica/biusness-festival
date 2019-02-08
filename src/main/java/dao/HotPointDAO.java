package dao;

import models.Festival;
import models.HotPoint;

import java.util.List;

public interface HotPointDAO {
    HotPoint getById(long id);
    Long save(HotPoint hotPoint);
    void remove(long id);
    void update(HotPoint hotPoint);
    List<HotPoint> getAllList();
    List<HotPoint> getAllByFestival(Festival festival);
}
