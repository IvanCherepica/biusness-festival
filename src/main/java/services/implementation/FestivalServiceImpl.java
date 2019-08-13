package services.implementation;

import dao.SessionFactoryHolder;
import dao.abstraction.FestivalDao;
import dao.implementation.FestivalDaoImpl;
import models.Festival;
import services.abstraction.FestivalService;

import java.util.List;

public class FestivalServiceImpl implements FestivalService {
	
	private final FestivalDao festivalDao;
	
	private static volatile FestivalServiceImpl instance;
	
	private FestivalServiceImpl() {
		this.festivalDao = new FestivalDaoImpl(SessionFactoryHolder.getSessionFactory());
	}
	
	public static FestivalServiceImpl getInstance() {
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
	public Long add (Festival festival) {

		Long festivalID = festivalDao.add(festival);
		if (festivalID==null) {
			System.out.println("Fesival " + festival.getName() + " is allready exist!");
			return getByName(festival.getName()).getId();

		}
		return festivalID;
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

}
