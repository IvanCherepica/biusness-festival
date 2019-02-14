package dao.implementation;

import dao.AbstactDAO;
import dao.abstraction.UserDAO;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}
