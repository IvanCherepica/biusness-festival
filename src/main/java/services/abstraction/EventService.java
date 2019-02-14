package services.abstraction;

import models.Event;

import java.util.List;

public interface EventService {
	Event getById(long id);
	Event getByName(String name);
	Long add(Event event);
	void update(Event event);
	void remove(long id);
	
	List<Event> getAllList();
	List<Event> getAllByEventPoint(long id);
	List<Event> getAllByFestival(long festivalId);
}
