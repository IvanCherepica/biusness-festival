package dao.implementation;

import dao.AbstactDAO;
import dao.abstraction.UserDAO;
import models.Event;
import models.User;
import org.hibernate.*;

import java.util.List;

public class UserDAOImpl extends AbstactDAO<User> implements UserDAO {

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<User> getAllList() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = null;
        try {
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of users: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public List<Event> getUserSchedule(long userId) {
        Session session = sessionFactory.openSession();
        String queryString = "SELECT * FROM events WHERE id IN (SELECT events_id FROM users_on_event WHERE users_id = :userId)";
        Transaction transaction = session.beginTransaction();
        List<Event> eventList = null;
        try {

            SQLQuery query = session.createSQLQuery(queryString);
            query.addEntity(Event.class);
            query.setParameter("userId", userId);
            eventList = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get user schedule: " + e.getMessage());
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return eventList;
    }
}
