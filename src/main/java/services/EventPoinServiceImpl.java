package services;

import dao.EventPointDAO;
import dao.EventPointDAOImpl;
import models.EventPoint;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class EventPoinServiceImpl implements EventPoinService {
    private final EventPointDAO eventPointDAO;

    private static volatile EventPoinServiceImpl instance;

    private EventPoinServiceImpl() {
        this.eventPointDAO = new EventPointDAOImpl(createSessionFactory(DBHelper.getConfiguration()));
    }

    public static EventPoinServiceImpl getInstance() {
        if (instance == null) {
            synchronized (EventPoinServiceImpl.class) {
                if (instance == null) {
                    instance = new EventPoinServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public EventPoint getById(long id) {
        return eventPointDAO.getById(id);
    }

    @Override
    public EventPoint getByName(String name) {
        return eventPointDAO.getByName(name);
    }

    @Override
    public Long add(EventPoint eventPoint) {
        Long eventPointID = eventPointDAO.add(eventPoint);
        if (eventPointID == null) {
            System.out.println("HotPoint " + eventPoint.getName() + " is allready exist!");
            return getByName(eventPoint.getName()).getId();
        }
        return eventPointID;
    }

    @Override
    public void update(EventPoint eventPoint) {
        eventPointDAO.update(eventPoint);
    }

    @Override
    public List<EventPoint> getAllList() {
        return eventPointDAO.getAllList();
    }

    @Override
    public void remove(long id) {
        eventPointDAO.remove(id);
    }

    @Override
    public List<User> getUsersByEventId(long id){
        EventPoint event= eventPointDAO.getById(id);
        return event.getUsersFromEvent();
    }
    @Override
    public void addUsersToEventId(long id, List<User> users){
        EventPoint event= eventPointDAO.getById(id);
        event.setUsersToEvent(users);
    }

    @Override
    public void addUserToEventId(long id, User user){
        EventPoint event= eventPointDAO.getById(id);
        event.addUserToEvent(user);
    }
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
