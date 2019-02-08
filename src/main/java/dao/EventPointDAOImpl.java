package dao;

import org.hibernate.SessionFactory;

public class EventPointDAOImpl extends AbstactDAO<EventPointDAOImpl> implements EventPointDAO<EventPointDAOImpl>{

    public EventPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
