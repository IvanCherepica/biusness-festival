package services;

import dao.FestivalDao;
import dao.FestivalDaoImpl;
import models.Festival;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class FestivalServiceImpl implements FestivalService {
	
	private final FestivalDao festivalDao;
	
	private static volatile FestivalServiceImpl instance;
	
	private FestivalServiceImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		this.festivalDao = new FestivalDaoImpl(createSessionFactory(DBHelper.getConfiguration()));
	}
	
	public static FestivalServiceImpl getInstance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		if (instance == null) {
			synchronized (FestivalServiceImpl.class) {
				if (instance == null) {
					instance = new FestivalServiceImpl();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Festival getById (long id) {
		return festivalDao.getById(id);
	}
	
	@Override
	public Festival getByName (String name) {
		return festivalDao.getByName(name);
	}
	
	@Override
	public void add (Festival festival) {
		festivalDao.add(festival);
	}
	
	@Override
	public void update (Festival festival) {
		festivalDao.update(festival);
	}
	
	@Override
	public void remove (long id) {
		festivalDao.remove(id);
	}
	
	@Override
	public List<Festival> getAllList () {
		return festivalDao.getAllList();
	}
	
	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
