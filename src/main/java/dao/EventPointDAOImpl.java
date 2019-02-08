package dao;

import models.EventPoint;
import org.hibernate.SessionFactory;

public class EventPointDAOImpl extends AbstactDAO<EventPoint> implements EventPointDAO{

    public EventPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
