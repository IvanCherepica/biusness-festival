package dao;

import models.HotPoint;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HotPointDAOImpl extends AbstactDAO<HotPoint>  implements HotPointDAO {

    public HotPointDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}
