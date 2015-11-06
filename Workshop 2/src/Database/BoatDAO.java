package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import workshop.Boat;
import workshop.Member;

import java.util.List;


public class BoatDAO implements databaseInterface<Boat> {
	private final Session session;
	
	public BoatDAO(Session session){
		this.session = session;
	}
	
	@Override
	public void update(Boat entity) {
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
	}

	@Override
	public void delete(Boat entity) {
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
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
		Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
	}

	@Override
	public Boat findById(long id) {
		return (Boat) session.get(Boat.class, id);
		
	}

}
