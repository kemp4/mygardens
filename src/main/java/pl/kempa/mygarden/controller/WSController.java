package pl.kempa.mygarden.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kempa.mygarden.dao.PlayerDaoImpl;
import pl.kempa.mygarden.model.Player;
@RestController
public class WSController {
	@RequestMapping("/resource")
	public Map<String,Object> home() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
	  @RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
		
	    @Autowired
	   	PlayerDaoImpl userdao;
		
	    @RequestMapping(value="/ws/register",method = RequestMethod.POST)
	    public @ResponseBody String register(
	    		 @RequestBody Map<String, Object> newuser  
	    		) 
	    	    throws Exception {

	    	Map<String, Object> player =  (Map<String, Object>) newuser.get("newuser");

	    	Player user=new Player();
	    	user.setEmail((String) player.get("email"));
	    	user.setPassword((String) player.get("password"));
	    	user.setUsername((String) player.get("username"));
	  
	    	userdao.register(user);
	    	
	        return "{\"success\":\"true\"}";
	    }
}
