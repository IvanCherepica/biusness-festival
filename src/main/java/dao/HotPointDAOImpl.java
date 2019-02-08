package dao;

import models.Festival;
import models.HotPoint;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class HotPointDAOImpl extends AbstactDAO<HotPointDAOImpl>  {//implements HotPointDAO<HotPoint> {


    public HotPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Long save(HotPoint hotPoint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long hpID = null;
        try {
            hpID = (Long) session.save(hotPoint);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t save HotPoint: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return hpID;
    }

    @Override
    public void remove(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        HotPoint hotPoint;
        try {
            Query query = session.createQuery("SELECT u FROM hot_point u WHERE u.id = :id");
            query.setParameter("id", id);

            hotPoint = (HotPoint) query.uniqueResult();

            if (hotPoint != null) {
                session.delete(hotPoint);
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t remove hotPoint: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

    }


    public void update(HotPoint hotPoint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(hotPoint);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t update HotPoint: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }


    public List<HotPoint> getAllList () {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<HotPoint> hotpoints = null;
        try {
            hotpoints = session.createQuery("FROM hot_point").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of Hot-Points: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return hotpoints;
     }


    public List<HotPoint> getAllByFestival (Festival festival) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<HotPoint> hotpoints = null;
        try {

            Query query = session.createQuery("FROM hot_point h WHERE h.id = :id");
            query.setParameter("id", festival.getId());
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of Hot-Points: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return hotpoints;
    }
}
