package pl.kempa.mygarden.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.kempa.mygarden.dao.PlayerDaoImpl;
import pl.kempa.mygarden.model.Player;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService,PlayerService {


	@Autowired
	private PlayerDaoImpl userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username)
		throws UsernameNotFoundException {
		Player user = userDao.getUserByUsername(username);
		return user;
	}

	@Override
	public void register(Player player) {
		userDao.register(player);
		
	}

	@Override
	public Player getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteGold(String username,int amount) throws Exception  {
		Player player = getUserByUsername(username);
		player.setGold(player.getGold()-amount);
		if (player.getGold()<0){
			throw new Exception();
		}
	}

	@Override
	public int getGold(String username) {
		Player player = getUserByUsername(username);
		return player.getGold();
	}

	
	


}
