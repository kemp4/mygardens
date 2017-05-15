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
public class MyUserDetailsService implements UserDetailsService {


	@Autowired
	private PlayerDaoImpl userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username)
		throws UsernameNotFoundException {
		Player user = userDao.getUserByUsername(username);
		return user;
	}



}
