package pl.kempa.mygarden.services;

import org.springframework.stereotype.Service;

import pl.kempa.mygarden.model.Player;
@Service
public interface PlayerService {
	public void register(Player player);
	public Player getUserByUsername(String username);
	public void deleteGold(String username,int amount) throws Exception;
	public int getGold(String username);

}
