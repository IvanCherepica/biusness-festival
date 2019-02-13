package services;


import dao.UserDAO;
import dao.UserDAOImpl;
import models.EventPoint;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    private static volatile UserServiceImpl instance;

    private UserServiceImpl() {
        this.userDAO = new UserDAOImpl(createSessionFactory(DBHelper.getConfiguration()));
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userDAO.getByName(name);
    }

    @Override
    public Long add(User user) {

        Long userId = userDAO.add(user);
        if (userId == null) {
            System.out.println("User " + user.getName() + " is allready exist!");
            return getByName(user.getName()).getId();
        }
        return userId;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void remove(long id) {
        userDAO.remove(id);
    }

    @Override
    public List<User> getAllList() {
        return userDAO.getAllList();
    }

    @Override
    public Set<EventPoint> getEventsFromUserId(long id){
        User user = userDAO.getById(id);
        return user.getEventPoints();
    }

    @Override
    public void addEventsListToUserbyId(long id,Set<EventPoint> events){
        User user= userDAO.getById(id);
        user.setEventPoints(events);
    }
    @Override
    public void addEventToUserId(long id, EventPoint event){
        User user= userDAO.getById(id);
        user.addEventPoint(event);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
