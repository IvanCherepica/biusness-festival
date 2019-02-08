package dao;

import models.HotPoint;

public interface HotPointDAO {
    HotPoint getById(long id);
	void save(HotPoint hotPoint);
    void remove(long id);
    void update(HotPoint t);
}
