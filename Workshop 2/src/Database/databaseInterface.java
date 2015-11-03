package Database;

import java.util.List;

import workshop.Member;

public interface databaseInterface <T> {

		
		public void update(T entity);

		public void delete(T entity);

		public void retrieve (long id);

		public List<T> findAll();

		public void save(T entity);

		public T findById(long id);
		
		
		

	}

