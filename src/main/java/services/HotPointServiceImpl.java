package services;


import dao.HotPointDAOImpl;
import models.HotPoint;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;


public class HotPointServiceImpl implements HotPointService{
    private final HotPointDAOImpl hotPointDAO;

    private static volatile HotPointServiceImpl instance;

    private HotPointServiceImpl() {
        this.hotPointDAO = new HotPointDAOImpl(createSessionFactory(DBHelper.getConfiguration()));
    }

    public static HotPointServiceImpl getInstance() {
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
    public HotPoint getByName(String name) {
        return hotPointDAO.getByName(name);
    }

    @Override
    public Long add(HotPoint hotPoint) {
        Long hotPointID = hotPointDAO.add(hotPoint);
        if (hotPointID==null) {
            System.out.println("HotPoint " + hotPoint.getName() + " is allready exist!");
            return getByName(hotPoint.getName()).getId();
        }
        return hotPointID;
    }

    @Override
    public void update(HotPoint hotPoint) {
        hotPointDAO.update(hotPoint);
    }

    @Override
    public void remove(long id) {
        hotPointDAO.remove(id);
    }
    
//    @Override
//    public List<HotPoint> getAllList () {
//        return hotPointDAO.getAllList();
//    }
//
//    @Override
//    public List<HotPoint> getAllByFestival (Festival festival) {
//        return hotPointDAO.getAllByFestival(festival);
//    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
