package pl.kempa.mygarden.dao;

import pl.kempa.mygarden.model.User;

public interface UserDaoInterface {
	public void register(User user);
	public User getUserByUsername(String username);
	
}
