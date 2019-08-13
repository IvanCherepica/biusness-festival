package dao.implementation;

import dao.AbstactDAO;
import dao.abstraction.EventDAO;
import models.Event;
import org.hibernate.*;

import java.sql.Timestamp;
import java.util.List;

public class EventDAOImpl extends AbstactDAO<Event> implements EventDAO {
	
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
				"eventpoint_id = :eventPoint, " +
				"festival_id = :festival, " +
				"date_begin = :dateBegin, " +
				"date_end = :dateEnd " +
				"WHERE id = :id";
		
		query = session.createQuery(queryString);
		query.setParameter("id", event.getId());
		query.setParameter("name", event.getName());
		query.setParameter("description", event.getDescription());
		query.setParameter("eventPoint", event.getEventPoint().getId());
		query.setParameter("festival", event.getFestival().getId());
//		query.setParameter("dateBegin", Timestamp.valueOf(event.getDateBegin()));
//		query.setParameter("dateEnd", Timestamp.valueOf(event.getDateEnd()));
		
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
			System.out.println("Can`t get list of Events by Eventpoint: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		return events;
	}
	
	public List<Event> getAllByFestival(long festivalId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List<Event> events = null;
		try {
			Query query = session.createQuery("FROM Event where festival_id = :id");
			//query.addEntity(Event.class);
			query.setParameter("id", festivalId);
			events = query.list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get list of Events by Festival: " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return events;
		
	}

	public void addEventToUser (Long userID, Long eventID) {
		Session session = sessionFactory.openSession();
		String queryString = "INSERT INTO users_on_event (users_id, events_id) VALUES (:userId, :eventId)";
		Transaction transaction = session.beginTransaction();
		try {

			Query query = session.createSQLQuery(queryString);
			query.setParameter("userId", userID);
			query.setParameter("eventId", eventID);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t update users_on_event: " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public void addRemoveEventFromUser (Long userID, Long eventID) {
		Session session = sessionFactory.openSession();
		String queryString = "DELETE FROM users_on_event WHERE users_id =:userId AND events_id = :eventId";
		Transaction transaction = session.beginTransaction();
		try {

			Query query = session.createSQLQuery(queryString);
			query.setParameter("userId", userID);
			query.setParameter("eventId", eventID);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t update users_on_event: " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
}
