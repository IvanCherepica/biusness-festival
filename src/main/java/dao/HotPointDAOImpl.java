package dao;

import models.HotPoint;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HotPointDAOImpl implements HotPointDAO {
    //private Session session;
    private SessionFactory sessionFactory;

    public HotPointDAOImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public HotPoint getById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        HotPoint hotPoint = null;
        try {
            hotPoint = (HotPoint) session.get(HotPoint.class,id);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get HotPoint by id: " + e.getMessage());
            transaction.rollback();
        } finally {
                session.close();
            }
        return  hotPoint;
    }

    @Override
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
            Query query = session.createQuery("SELECT u FROM HotPoint u WHERE u.id = :id");
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

    @Override
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
}
