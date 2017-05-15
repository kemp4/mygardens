package pl.kempa.mygarden.dao;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kempa.mygarden.model.User;
@Repository
@Transactional
public class UserDao implements UserDaoInterface {
	@PersistenceContext	
	private EntityManager entityManager;
	

	
    @Override
	public void register(User user) {
		entityManager.persist(user);
	}
	@Override
	 public User getUserByUsername(String username) {
		
		String hql = "FROM User  WHERE username = :un";
		  Query query = entityManager
				.createQuery(hql)
				.setParameter("un", username);
		  User usr= (User) query.getSingleResult();

		 return  usr;
	}
	public User getUserById(int id) {
	    return entityManager.find(User.class, id);
	}


}
