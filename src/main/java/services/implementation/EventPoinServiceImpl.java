package services.implementation;

import dao.SessionFactoryHolder;
import dao.abstraction.EventPointDAO;
import dao.implementation.EventPointDAOImpl;
import models.EventPoint;
import services.abstraction.EventPoinService;

import java.util.List;

public class EventPoinServiceImpl implements EventPoinService {

    private final EventPointDAO eventPointDAO;

    private static volatile EventPoinServiceImpl instance;

    private EventPoinServiceImpl() {
        this.eventPointDAO = new EventPointDAOImpl(SessionFactoryHolder.getSessionFactory());
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
    public void remove(long id) {
        eventPointDAO.remove(id);
    }
    
    @Override
    public List<EventPoint> getAllList() {
        return eventPointDAO.getAllList();
    }
    
    @Override
    public List<EventPoint> getAllByFestival (long id) {
        return eventPointDAO.getAllByFestival(id);
    }
    
    @Override
    public List<EventPoint> getAllEventPointByFestivalId(long festivalId) {
        return eventPointDAO.getAllEventPointByFestivalId(festivalId);
    }

}
