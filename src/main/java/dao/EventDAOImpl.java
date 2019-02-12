package dao;

import models.Event;
import org.hibernate.*;

import java.util.List;

public class EventDAOImpl extends AbstactDAO<Event> implements EventDAO{
	
	public EventDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void update (Event event) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Event WHERE id = :id");
		query.setParameter("id", event.getId());
		
		
		if (query.uniqueResult() == null) {
			throw new HibernateException("No such element");
		}
		
		String queryString = "UPDATE Event SET name = :name, " +
				"description = :description, " +
				"eventpoint_id = :eventPoint " +
				"WHERE id = :id";
		
		query = session.createQuery(queryString);
		query.setParameter("id", event.getId());
		query.setParameter("name", event.getName());
		query.setParameter("description", event.getDescription());
		query.setParameter("eventPoint", event.getEventPoint().getId());
		
		Transaction transaction = session.beginTransaction();
		query.executeUpdate();
		transaction.commit();
	}
	
	@Override
	public List<Event> getAllList () {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List<Event> events = null;
		try {
			events = session.createQuery("FROM Event").list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get list of Events: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return events;
	}
	
	@Override
	public List<Event> getAllByEventPoint (long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List<Event> events = null;
		try {
			SQLQuery query = session.createSQLQuery("select * from events where eventpoint_id = :id");
			query.addEntity(Event.class);
			query.setParameter("id", id);
			events = query.list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get list of eventPoints: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		return events;
	}
}
