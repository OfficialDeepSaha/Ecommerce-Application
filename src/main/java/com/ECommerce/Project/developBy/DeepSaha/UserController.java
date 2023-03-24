package com.ECommerce.Project.developBy.DeepSaha;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	private Userservice userService;
	
	
	
	
	
     @GetMapping("/login")
	public String viewpage(Principal principal ) {
		if(principal!=null) {
	
			
		}
    	 
		return "login";
	}
     
     @GetMapping("/register")
     public String registeruser(Model model)throws ServletException ,  UnsupportedEncodingException {
    	 User user = new User();
    	 model.addAttribute("user", user);
    	 return "register";
    	 
     }
     
     
     @PostMapping("/register")
     public String registration_process(@ModelAttribute("user") User user) {
    	
    	 userService.registeruser(user);
    	
    	 
    
    	 return"register_success";
    	 
    	 
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
