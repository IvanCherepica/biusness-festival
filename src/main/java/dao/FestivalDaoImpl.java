package dao;

import models.Festival;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FestivalDaoImpl implements FestivalDao{
	private SessionFactory sessionFactory;
	
	public FestivalDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Festival getById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Festival user = null;
		try {
			user = (Festival) session.get(Festival.class, id);
			
			//call save(), persist() or saveOrUpdate() method, to change rows in db
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get Festival by id: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return user;
	}
	
	@Override
	public Festival getByName (String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Festival festival = null;
		
		try {
			Query query = session.createQuery("SELECT f FROM festival f WHERE f.name = :name");
			query.setParameter("name", name);
			
			festival = (Festival) query.uniqueResult();
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t find festival: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		return festival;
	}
	
	@Override
	public void add (Festival festival) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.save(festival);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t add festival: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void update (Festival festival) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.update(festival);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t update festival: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void remove (long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Festival festival;
		try {
			Query query = session.createQuery("SELECT f FROM festival f WHERE f.id = :id");
			query.setParameter("id", id);
			
			festival = (Festival) query.uniqueResult();
			
			if (festival != null) {
				session.delete(festival);
			}
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t remove festival: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
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
