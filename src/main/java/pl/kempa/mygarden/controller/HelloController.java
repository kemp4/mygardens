
package pl.kempa.mygarden.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.kempa.mygarden.dao.PlayerDaoImpl;
import pl.kempa.mygarden.model.Player;


public class HelloController {
	

	@Value("${game.title:test}")
	private String message = "Hello World";
	
	@RequestMapping("/home")
	public String home(Map<String, Object> model) {
		model.put("message", this.message);
		return "home2";
	}
	@RequestMapping("/signin2")
	public String signin(Map<String, Object> model) {
		return "signin";
	}
	@RequestMapping("/signup2")
	public String signup(Map<String, Object> model) {
		return "signup";
	}
	@RequestMapping("/ranking2")
	public String ranking(Map<String, Object> model) {
		List<Player> players= userdao.getAllUsers();
		
		model.put("players", players);
		return "ranking";
	}
	  @RequestMapping("/signin-error.html")
	  public String loginError(Map<String, Object> model) {
	    model.put("loginError", true);
	    return "signin.html";
	  }
	@RequestMapping(value="/login2",method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String login
    		,@RequestParam(value = "password")String pass,Map<String, Object> model) {
		Player someUser=userdao.getUserByUsername(login);
   
    	Player user=new Player();
    	user.setEmail("dupa@du.du");
    	user.setPassword(pass);
    	user.setUsername(login);
    	if (!(someUser.getPassword().equals(user.getPassword()))){
    		someUser.setSuccess(false);
    	}else{
    		someUser.setSuccess(true);
    	}
    	model.put("username",user.getUsername());
    	return "main";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
    public String register(
    		@RequestParam(value = "username") String login
    		,@RequestParam(value = "email")String email
    		,@RequestParam(value = "password")String pass
    		,Map<String, Object> model) {

    	Player user=new Player();
    	user.setEmail(email);
    	user.setPassword(pass);
    	user.setUsername(login);
  
    	userdao.register(user);
    	model.put("message","registeredddd");
    	
    	
        return "home2";
    }
	
	
    @Autowired
   	PlayerDaoImpl userdao;
	
    @RequestMapping(value="/ws/register",method = RequestMethod.POST)
    public @ResponseBody String register(
    		@RequestParam(value = "username") String login
    		,@RequestParam(value = "email")String email
    		,@RequestParam(value = "password")String pass) {

    	Player user=new Player();
    	user.setEmail(email);
    	user.setPassword(pass);
    	user.setUsername(login);
  
    	userdao.register(user);
    	
        return "{\"success\":\"true\"}";
    }
    @RequestMapping(value="/ws/login",method = RequestMethod.POST)
    public @ResponseBody Player login(
    		@RequestParam(value = "username") String login
    		,@RequestParam(value = "password")String pass) {

    	Player someUser=userdao.getUserByUsername(login);
   
    	Player user=new Player();
    	user.setEmail("dupa@du.du");
    	user.setPassword(pass);
    	user.setUsername(login);
    	if (!(someUser.getPassword().equals(user.getPassword()))){
    		someUser.setSuccess(false);
    	}else{
    		someUser.setSuccess(true);
    	}
        return someUser;
    }
    @RequestMapping(value="/ws/buyseeds",method = RequestMethod.POST)
    public @ResponseBody Player buySeeds(
    		@RequestParam(value = "id") int id
    		,@RequestParam(value = "amount")int amount
    		//,@RequestParam(value = "type")String pass
    		) 
    {
    	Player someUser=userdao.getUserById(id);
        return someUser;
    }
    
    @RequestMapping(value="/ws/userdata",method = RequestMethod.POST)
    public @ResponseBody Player getUserData(
    		@RequestParam(value = "id") int id
    		//,@RequestParam(value = "amount")int id
    		//,@RequestParam(value = "type")String pass
    		) 
    {
    	Player someUser=userdao.getUserById(id);
        return someUser;
    }
}
