package dao;

import models.EventPoint;
import models.HotPoint;
import org.hibernate.*;

public class EventPointDAOImpl extends AbstactDAO<EventPoint> implements EventPointDAO{

    public EventPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    @Override
    public void update (EventPoint item) {
        
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
        query.setParameter("id",        item.getId());
        query.setParameter("name",      item.getName());
        query.setParameter("description", item.getDescription());
        query.setParameter("geometry",  item.getGeometry());
        query.setParameter("color",     item.getColor());
        query.setParameter("festival",  item.getFestival().getId());
        
        Transaction transaction = session.beginTransaction();
        query.executeUpdate();
        transaction.commit();
    }
}
