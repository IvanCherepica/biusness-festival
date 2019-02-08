package services;

import dao.HotPointDAO;
import dao.HotPointDAOImpl;
import models.HotPoint;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.sql.SQLException;

public class HotPointServiceImpl implements HotPointService{
    private final HotPointDAO hotPointDAO;

    private static volatile HotPointServiceImpl instance;

    private HotPointServiceImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.hotPointDAO = new HotPointDAOImpl(createSessionFactory(DBHelper.getConfiguration()));
    }

    public static HotPointServiceImpl getInstance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            synchronized (FestivalServiceImpl.class) {
                if (instance == null) {
                    instance = new HotPointServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public HotPoint getById(long id) {
        return hotPointDAO.getById(id);
    }

    @Override
    public Long add(HotPoint hotPoint) {
        return hotPointDAO.save(hotPoint);
    }

    @Override
    public void update(HotPoint hotPoint) {
        hotPointDAO.update(hotPoint);
    }

    @Override
    public void remove(long id) {
        hotPointDAO.remove(id);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
