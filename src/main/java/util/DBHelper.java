package util;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

	static public Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

		DriverManager.registerDriver((Driver) Class.forName(PropertiesReader.getProperties("driver.class")).newInstance());

		String url = PropertiesReader.getProperties("connection.url") + "?user=" +
				PropertiesReader.getProperties("username") + "&password=" +
				PropertiesReader.getProperties("password");

		return DriverManager.getConnection(url);

	}

	static public Configuration getConfiguration() {
		Configuration configuration = new Configuration();
		//configuration.addAnnotatedClass(User.class);

		configuration.setProperty("hibernate.dialect", PropertiesReader.getProperties("dialect"));
		configuration.setProperty("hibernate.connection.driver_class", PropertiesReader.getProperties("driver.class"));
		configuration.setProperty("hibernate.connection.url", PropertiesReader.getProperties("connection.url"));
		configuration.setProperty("hibernate.connection.username", PropertiesReader.getProperties("username"));
		configuration.setProperty("hibernate.connection.password", PropertiesReader.getProperties("password"));
		configuration.setProperty("hibernate.show_sql", PropertiesReader.getProperties("show_sql"));
		configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesReader.getProperties("hbm2ddl.create"));
		return configuration;
	}
}
