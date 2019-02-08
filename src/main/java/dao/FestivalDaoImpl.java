package dao;

import models.Festival;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FestivalDaoImpl extends AbstactDAO<Festival> implements FestivalDao{
	private SessionFactory sessionFactory;

	public FestivalDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);

	}
	
	
	@Override
	public List<Festival> getAllList () {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List<Festival> festivals = null;
		try {
			festivals = session.createQuery("FROM festival").list();
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
