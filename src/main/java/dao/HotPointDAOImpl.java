package dao;

import models.HotPoint;
import org.hibernate.Session;

public class HotPointDAOImpl implements HotPointDAO {
    private Session session;

    public HotPointDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public HotPoint getById(long id) {
        return (HotPoint) session.get(HotPoint.class,id);
    }

    @Override
    public long save(HotPoint hotPoint) {
        return (long) session.save(hotPoint);
    }

    @Override
    public void remove(long id) {
        Object persistentInstance = session.load(HotPoint.class,id);
        session.delete(persistentInstance);
    }

    @Override
    public void update(HotPoint hotPoint) {
        session.update(hotPoint);
    }
}
