package util;

import models.EventPoint;
import models.Festival;
import models.HotPoint;
import models.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {


	static public Configuration getConfiguration() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Festival.class);
		configuration.addAnnotatedClass(HotPoint.class);
		configuration.addAnnotatedClass(EventPoint.class);
		configuration.addAnnotatedClass(User.class);

		configuration.setProperty("hibernate.dialect", PropertiesReader.getProperties("dialect"));
		configuration.setProperty("hibernate.connection.driver_class", PropertiesReader.getProperties("driver.class"));
		configuration.setProperty("hibernate.connection.url", PropertiesReader.getProperties("connection.url"));
		configuration.setProperty("hibernate.connection.username", PropertiesReader.getProperties("username"));
		configuration.setProperty("hibernate.connection.password", PropertiesReader.getProperties("password"));
		configuration.setProperty("hibernate.show_sql", PropertiesReader.getProperties("show_sql"));
		configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesReader.getProperties("hbm2ddl.auto"));
		return configuration;
	}
}
