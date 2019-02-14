package dao.implementation;

import dao.AbstactDAO;
import dao.abstraction.EventPointDAO;
import models.EventPoint;
import org.hibernate.*;

import java.util.List;

public class EventPointDAOImpl extends AbstactDAO<EventPoint> implements EventPointDAO {

    public EventPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void update(EventPoint item) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM EventPoint WHERE id = :id");
        query.setParameter("id", item.getId());


        if (query.uniqueResult() == null) {
            throw new HibernateException("No such element");
        }

        String queryString = "UPDATE EventPoint SET name = :name, " +
                "description = :description, " +
                "geometry = :geometry, " +
                "color = :color, " +
                "festival_id = :festival " +
                "WHERE id = :id";

        query = session.createQuery(queryString);
        query.setParameter("id", item.getId());
        query.setParameter("name", item.getName());
        query.setParameter("description", item.getDescription());
        query.setParameter("geometry", item.getGeometry());
        query.setParameter("color", item.getColor());
        query.setParameter("festival", item.getFestival().getId());

        Transaction transaction = session.beginTransaction();
        query.executeUpdate();
        transaction.commit();
    }
    
    @Override
    public List<EventPoint> getAllList() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<EventPoint> eventPoints = null;
        try {
            eventPoints = session.createQuery("FROM EventPoint").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of eventPoints: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return eventPoints;
    }
    
    @Override
    public List<EventPoint> getAllByFestival (long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    
        List<EventPoint> eventPoints = null;
        try {

            SQLQuery query = session.createSQLQuery("select * from event_point where festival_id = :id");
            query.addEntity(EventPoint.class);
            query.setParameter("id", id);
            eventPoints = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of eventPoints: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return eventPoints;
    }
    
    public List<EventPoint> getAllEventPointByFestivalId(long festivalId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    
        List<EventPoint> eventPoints = null;
        try {
            SQLQuery query = session.createSQLQuery("select * from event_point where festival_id = :id");
            query.addEntity(EventPoint.class);
            query.setParameter("id", festivalId);
            eventPoints = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of eventPoints: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return eventPoints;
    }

    public void clearCash () {
        Cache cache = sessionFactory.getCache();

        if (cache != null) {
            cache.evictAllRegions(); // Evict data from all query regions.
        }
    }
}
