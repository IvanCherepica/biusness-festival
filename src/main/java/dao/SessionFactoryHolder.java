package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.sql.SQLException;

public class SessionFactoryHolder {

    private static volatile SessionFactory instance;

    private SessionFactoryHolder() {

    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            synchronized (SessionFactoryHolder.class) {
                if (instance == null) {
                    instance = createSessionFactory(DBHelper.getConfiguration());
                }
            }
        }
        return instance;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
