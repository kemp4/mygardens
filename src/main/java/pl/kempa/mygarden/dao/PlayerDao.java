package pl.kempa.mygarden.dao;

import pl.kempa.mygarden.model.Player;


public interface PlayerDao {
	public void register(Player Player);
	public Player getUserByUsername(String username);

}