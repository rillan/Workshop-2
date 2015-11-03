package Database;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import workshop.Member;

public class MemberDAO implements databaseInterface<Member> {
		private final Session session;  
		
		public  MemberDAO(Session session){
		this.session = session;
		}
	@Override
	public void update(Member entity) {
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
	}
	
	@Override
	public Member findById(long id) {
		return (Member) session.get(Member.class, id);
	}

	@Override
	public void delete(Member entity) {
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
	}

	@Override
	public void retrieve(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Member> findAll() {
		
		
		return session.createCriteria(Member.class).list();
		
	}
	@Override
	public void save(Member entity) {
		Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
		
		
	
	
     
	}

}

