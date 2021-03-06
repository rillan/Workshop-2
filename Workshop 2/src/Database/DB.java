package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javafx.fxml.FXML;
import model.Member;
import view.Main;

public class DB {

	public static SessionFactory sessionFactory;
	public static Session session;
	public static BoatDAO BoatDAO;
	public static MemberDAO MemberDao;
	public static mainDAO mainDao;
	
	static {
		try {
			Configuration configuration = new Configuration().configure();
			
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static Session getSession() {
		if (session == null)
			session = sessionFactory.openSession();

		return session;
	}

	public static BoatDAO boats() {
		if (BoatDAO == null)
			BoatDAO = new BoatDAO(getSession());

		return BoatDAO;
	}

	public static MemberDAO members() {
		if (MemberDao == null)
			MemberDao = new MemberDAO(getSession());

		return MemberDao;
	}
	
	public static mainDAO main() {
		if (mainDao == null)
			mainDao= new mainDAO(getSession());

		return mainDao;
	}
	
	@FXML
	public static  Main findById(long id) {
		return (Main) session.get(Main.class, id);
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}
}
