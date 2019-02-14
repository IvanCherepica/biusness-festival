package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;


public abstract class AbstactDAO<T> {
    private final Class<T> persistentClass;

    private String className;

    protected SessionFactory sessionFactory;

    public AbstactDAO(SessionFactory sessionFactory) {
        persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String genericClassName = persistentClass.toGenericString();
        this.className = genericClassName.substring(genericClassName.lastIndexOf('.')+1);
        this.sessionFactory = sessionFactory;
    }

    public T getById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T item = null;
        try {
            item = (T) session.get(persistentClass,id);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get " + className + " by id: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return  item;
    }

    public T getByName (String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        T item = null;

        try {
            Query query = session.createQuery("SELECT f FROM " + className + " f WHERE f.name = :name");
            query.setParameter("name", name);

            item = (T) query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t find " + className + ": " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return item;
    }

    public Long add (T item) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long itemID = null;
        try {
            itemID = (long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t add " + className + ": " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return itemID;
    }

    public void remove (long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        T item;
        try {
            Query query = session.createQuery("SELECT f FROM " + className + " f WHERE f.id = :id");
            query.setParameter("id", id);

            item = (T) query.uniqueResult();

            if (item != null) {
                session.delete(item);
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t remove " + className + ": " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void update (T item) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t update " + className + ": " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
