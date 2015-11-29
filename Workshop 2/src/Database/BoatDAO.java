package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Boat;
import model.Member;

import java.util.List;


public class BoatDAO implements databaseInterface<Boat> {
	private final Session session;
	
	public BoatDAO(Session session){
		this.session = session;
	}
	
	@Override
	public void update(Boat entity) {
		try{
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		}
		catch (RuntimeException e) {
			session.getTransaction().rollback();
		    throw e;
	
	
		}
	}

	@Override
	public void delete(Boat entity) {
	try{
		Transaction transaction = session.beginTransaction();
		
		session.delete(entity);
		transaction.commit();
	}
	catch (RuntimeException e) {
		session.getTransaction().rollback();
	    throw e;


	}
		
	}

	@Override
	public void retrieve(long id) {
		
	}

	@Override
	public List<Boat> findAll() {
		
		return session.createCriteria(Boat.class).list();
	}

	@Override
	public void save(Boat entity) {
		try{
		Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
		}
		catch (RuntimeException e) {
			session.getTransaction().rollback();
		    throw e;
	
	
		}
	}

	@Override
	public Boat findById(long id) {
		return (Boat) session.get(Boat.class, id);
		
	}

}
