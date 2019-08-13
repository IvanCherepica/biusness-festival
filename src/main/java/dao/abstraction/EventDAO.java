package dao.abstraction;

import models.Event;
import java.util.List;

public interface EventDAO {
	Event getById(long id);
	Long add(Event event);
	Event getByName(String name);
	void remove(long id);
	void update(Event event);
	void addEventToUser (Long userID, Long eventID);
	void addRemoveEventFromUser (Long userID, Long eventID);

	List<Event> getAllList();
	List<Event> getAllByEventPoint(long id);
	List<Event> getAllByFestival(long fetsivalId);
}
