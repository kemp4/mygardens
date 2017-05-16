package pl.kempa.mygarden.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kempa.mygarden.model.Player;
@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao {
	@PersistenceContext	
	private EntityManager entityManager;
	

	
    @Override
	public void register(Player user) {
		entityManager.persist(user);
	}
	@Override
	 public Player getUserByUsername(String username) {
		
		String hql = "FROM Player  WHERE username = :username";
		  Query query = entityManager
				.createQuery(hql)
				.setParameter("username", username);
		  Player usr= (Player) query.getSingleResult();

		 return  usr;
	}
	public Player getUserById(int id) {
	    return entityManager.find(Player.class, id);
	}
	public List<Player> getAllUsers() {
		//List players = null;
		String hql = "FROM Player";
		  TypedQuery<Player> query = entityManager
				.createQuery(hql,Player.class);
		  List<Player> usrs= query.getResultList();

		 return  usrs;
	}


}
