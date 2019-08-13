package services.implementation;

import dao.SessionFactoryHolder;
import dao.abstraction.EventDAO;
import dao.implementation.EventDAOImpl;
import models.Event;
import models.EventPoint;
import services.abstraction.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {
	private final EventDAO eventDAO;
	
	private static volatile EventServiceImpl instance;
	
	private EventServiceImpl() {
		this.eventDAO = new EventDAOImpl(SessionFactoryHolder.getSessionFactory());
	}
	
	public static EventServiceImpl getInstance() {
		if (instance == null) {
			synchronized (EventServiceImpl.class) {
				if (instance == null) {
					instance = new EventServiceImpl();
				}
			}
		}
		return instance;
	}
	
	public Event getById (long id) {
		return eventDAO.getById(id);
	}
	
	@Override
	public Event getByName (String name) {
		return eventDAO.getByName(name);
	}
	
	@Override
	public Long add (Event event) {
		return eventDAO.add(event);
	}
	
	@Override
	public void update (Event event) {
		eventDAO.update(event);
	}
	
	@Override
	public void remove (long id) {
		eventDAO.remove(id);
	}
	
	@Override
	public List<Event> getAllList () {
		return eventDAO.getAllList();
	}
	
	@Override
	public List<Event> getAllByEventPoint (long id) {
		return eventDAO.getAllByEventPoint(id);
	}
	
	public List<Event> getAllByFestival(long fetsivalId) {
		return eventDAO.getAllByFestival(fetsivalId);
	}

	public void addEventToUser (Long userID, Long eventID) {
		eventDAO.addEventToUser(userID,eventID);
	}


	public void addRemoveEventFromUser (Long userID, Long eventID) {
		eventDAO.addRemoveEventFromUser(userID,eventID);
	}


}
