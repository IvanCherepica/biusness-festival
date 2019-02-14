package dao.implementation;

import dao.AbstactDAO;
import dao.abstraction.FestivalDao;
import models.Festival;
import org.hibernate.*;

import java.util.List;

public class FestivalDaoImpl extends AbstactDAO<Festival> implements FestivalDao {
	
	public FestivalDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void update (Festival item) {
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM  Festival WHERE id = :id");
		query.setParameter("id", item.getId());
		
		if (query.uniqueResult() == null) {
			throw new HibernateException("No such element");
		}
		
		String queryString = "UPDATE Festival SET name = :name, " +
				"description = :description, " +
				"geometry = :geometry, " +
				"color = :color, " +
				"center = :center, " +
				"radius = :radius " +
				"WHERE id = :id";
		
		query = session.createQuery(queryString);
		query.setParameter("id",        item.getId());
		query.setParameter("name",      item.getName());
		query.setParameter("description", item.getDescription());
		query.setParameter("geometry",  item.getGeometry());
		query.setParameter("color",     item.getColor());
		query.setParameter("center", item.getCenter());
		query.setParameter("radius", item.getRadius());
		
		Transaction transaction = session.beginTransaction();
		query.executeUpdate();
		transaction.commit();
	}
	
	@Override
	public List<Festival> getAllList () {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List<Festival> festivals = null;
		try {
			festivals = session.createQuery("FROM Festival").list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get list of festivals: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		return festivals;
	}
}
