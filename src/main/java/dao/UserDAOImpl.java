package dao;


import models.User;
import org.hibernate.SessionFactory;

public class UserDAOImpl extends AbstactDAO<User> implements UserDAO  {

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
