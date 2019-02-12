package dao;

import models.Event;
import java.util.List;

public interface EventDAO {
	Event getById(long id);
	Long add(Event event);
	Event getByName(String name);
	void remove(long id);
	void update(Event event);
	
	List<Event> getAllList();
	List<Event> getAllByEventPoint(long id);
}
