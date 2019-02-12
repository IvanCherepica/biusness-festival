package services;

import dao.EventDAO;
import dao.EventDAOImpl;
import models.Event;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class EventServiceImpl implements EventService {
	private final EventDAO eventDAO;
	
	private static volatile EventServiceImpl instance;
	
	private EventServiceImpl() {
		this.eventDAO = new EventDAOImpl(createSessionFactory(DBHelper.getConfiguration()));
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
	
	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
