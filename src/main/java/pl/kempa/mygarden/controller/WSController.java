package pl.kempa.mygarden.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kempa.mygarden.dao.PlayerDaoImpl;
import pl.kempa.mygarden.model.Player;
import pl.kempa.mygarden.services.PlayerService;



@RestController
public class WSController {


	@Autowired
	PlayerService playerService;

	@RequestMapping("/resource")
	public Map<String,Object> home() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "hi user");
		return model;
	}
	@RequestMapping("/user")
	public Principal user(HttpSession session,Principal user) {

		return user;
	}

	//	@Autowired
	//	PlayerDaoImpl userdao;	

	@RequestMapping(value="/ws/register",method = RequestMethod.POST)
	public @ResponseBody String register(
			@RequestBody Map<String, Object> newuser  
			) 
					throws Exception {

		@SuppressWarnings("unchecked")
		Map<String, Object> player =  (Map<String, Object>) newuser.get("newuser");

		Player user=new Player();
		user.setEmail((String) player.get("email"));
		user.setPassword((String) player.get("password"));
		user.setUsername((String) player.get("username"));

		//userdao.register(user);
		playerService.register(user);

		return null;
		//return "{\"success\":\"true\"}";
	}

	@RequestMapping(value="/buy",method = RequestMethod.POST)
	public Map<String,Object> buy(Principal user, @RequestBody Map<String, String> params) 
			throws Exception {
		
		Map<String,Object> model = new HashMap<String,Object>();

		String cost =  params.get("cost");
		
		model.put("success", "true");
		model.put("message", "you bought something");
		
		try{
			playerService.deleteGold(user.getName(),Integer.parseInt(cost));
		}catch( Exception e){
			model.put("success", "false");
			model.put("message", "not enough gold");
		}


		return model;

		//return "{\"success\":\"true\"}";
	}
	@RequestMapping(value="/getplayergold",method = RequestMethod.POST)
	public Map<String,Object> getPlayerGold(Principal user) 
			throws Exception {
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		model.put("goldAmount",playerService.getGold(user.getName()));

		return model;
	}
}
