package services.implementation;


import dao.SessionFactoryHolder;
import dao.implementation.HotPointDAOImpl;
import models.HotPoint;
import services.abstraction.HotPointService;

import java.util.List;


public class HotPointServiceImpl implements HotPointService {
    private final HotPointDAOImpl hotPointDAO;

    private static volatile HotPointServiceImpl instance;

    private HotPointServiceImpl() {
        this.hotPointDAO = new HotPointDAOImpl(SessionFactoryHolder.getSessionFactory());
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
    
    @Override
    public List<HotPoint> getAllList () {
        return hotPointDAO.getAllList();
  }

    @Override
    public List<HotPoint> getAllByFestival (long id) {
        return hotPointDAO.getAllByFestival(id);
    }

}
