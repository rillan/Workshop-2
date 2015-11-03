package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import workshop.Boat;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boat findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
