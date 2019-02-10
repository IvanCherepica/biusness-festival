package services;


import dao.UserDAO;
import dao.UserDAOImpl;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

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
        if (userId==null) {
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



    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
