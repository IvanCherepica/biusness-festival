package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private static String fileName;

	private static String usedTech;

	private static void setFileName() {
		fileName = "application.properties";
		String usedTechFromProp = getProperties("used.tech");
		usedTech = usedTechFromProp;
		if (usedTechFromProp.equals("hibernate")) {
			fileName = "hibernate.properties";
		} else {
			fileName = "jdbc.properties";
		}
	}

	public static String getProperties(String name) {
		if (fileName == null) {
			setFileName();
		}
		if (usedTech != null && name.equals("used.tech")) {
			return usedTech;
		}
		String value = null;
		Properties properties = new Properties();
		PropertiesReader reader = new PropertiesReader();
		try(InputStream input = reader.getClass().getClassLoader().getResourceAsStream(fileName)){
			properties.load(input);
			value = properties.getProperty(name);
		} catch (IOException e) {
			System.out.println("Can`t read properties file");
		}
		return value;
	}
}
