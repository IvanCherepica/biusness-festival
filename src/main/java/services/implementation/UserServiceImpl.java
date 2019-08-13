package services.implementation;


import dao.SessionFactoryHolder;
import dao.abstraction.UserDAO;
import dao.implementation.UserDAOImpl;
import models.Event;
import models.EventPoint;
import models.User;
import services.abstraction.UserService;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    private static volatile UserServiceImpl instance;

    private UserServiceImpl() {
        this.userDAO = new UserDAOImpl(SessionFactoryHolder.getSessionFactory());
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

    public List<Event> getUserSchedule(long userId) {
        return userDAO.getUserSchedule(userId);
    }

//    @Override
//    public Set<EventPoint> getEventsFromUserId(long id){
//        User user = userDAO.getById(id);
//        return user.getEventPoints();
//    }
//
//    @Override
//    public void addEventsListToUserbyId(long id,Set<EventPoint> events){
//        User user= userDAO.getById(id);
//        user.setEventPoints(events);
//    }
//    @Override
//    public void addEventToUserId(long id, EventPoint event){
//        User user= userDAO.getById(id);
//        user.addEventPoint(event);
//    }

}
