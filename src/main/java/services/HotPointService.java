package services;

import models.Festival;
import models.HotPoint;

import java.util.List;

public interface HotPointService {
    HotPoint getById(long id);
    //Festival getByName(String name);
    Long add(HotPoint hotPoint);
    void update(HotPoint hotPoint);
    void remove(long id);
}
