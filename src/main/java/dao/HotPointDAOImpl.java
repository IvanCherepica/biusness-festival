package dao;

import models.HotPoint;
import org.hibernate.SessionFactory;

public class HotPointDAOImpl extends AbstactDAO<HotPoint>  implements HotPointDAO {

    public HotPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}
