package dao.implementation;

import dao.AbstactDAO;
import dao.abstraction.HotPointDAO;
import models.HotPoint;
import org.hibernate.*;

import java.util.List;

public class HotPointDAOImpl extends AbstactDAO<HotPoint> implements HotPointDAO {

    public HotPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    @Override
    public void update (HotPoint item) {
        
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM HotPoint WHERE id = :id");
        query.setParameter("id", item.getId());
        
        if (query.uniqueResult() == null) {
            throw new HibernateException("No such element");
        }
        
        String queryString = "UPDATE HotPoint SET name = :name, " +
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
    @Override
    public List<HotPoint> getAllList() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<HotPoint> hotPoints = null;
        try {
            hotPoints = session.createQuery("FROM HotPoint").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of eventPoints: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return hotPoints;
    }
    
    @Override
    public List<HotPoint> getAllByFestival (long id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        List<HotPoint> hotPoints = null;
        try {
            Query query = session.createQuery("FROM HotPoint WHERE festival_id=:id");
            query.setParameter("id", id);
            hotPoints = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of eventPoints: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        
        return hotPoints;
    }
}
