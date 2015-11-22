package Database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Member;

public class mainDAO implements databaseInterface<mainDAO> {
	private final Session session;  
	Transaction transaction;
	public mainDAO(Session session) {
		this.session = session;
	}

	@Override
	public void update(mainDAO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(mainDAO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieve(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<mainDAO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(mainDAO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public mainDAO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
