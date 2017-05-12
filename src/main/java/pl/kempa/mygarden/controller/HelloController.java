
package pl.kempa.mygarden.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.kempa.mygarden.dao.UserDao;
import pl.kempa.mygarden.model.User;
import pl.kempa.mygarden.model.ResponseStatus;

@Controller
public class HelloController {
    @Autowired
   	UserDao userdao;
	
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public @ResponseBody String register(
    		@RequestParam(value = "username") String login
    		,@RequestParam(value = "email")String email
    		,@RequestParam(value = "password")String pass) {

    	User user=new User();
    	user.setEmail(email);
    	user.setPassword(pass);
    	user.setUsername(login);
  
    	userdao.register(user);
    	
        return "{\"success\":\"true\"}";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public @ResponseBody User login(
    		@RequestParam(value = "username") String login
    		//,@RequestParam(value = "email")String email
    		,@RequestParam(value = "password")String pass) {

    	User someUser=userdao.getUserByUsername(login);
   
    	User user=new User();
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
    @RequestMapping(value="/buyseeds",method = RequestMethod.POST)
    public @ResponseBody User buySeeds(
    		@RequestParam(value = "id") int id
    		,@RequestParam(value = "amount")int amount
    		//,@RequestParam(value = "type")String pass
    		) 
    {
    	User someUser=userdao.getUserById(id);
        return someUser;
    }
    
    @RequestMapping(value="/userdata",method = RequestMethod.POST)
    public @ResponseBody User getUserData(
    		@RequestParam(value = "id") int id
    		//,@RequestParam(value = "amount")int id
    		//,@RequestParam(value = "type")String pass
    		) 
    {
    	User someUser=userdao.getUserById(id);
        return someUser;
    }
}
