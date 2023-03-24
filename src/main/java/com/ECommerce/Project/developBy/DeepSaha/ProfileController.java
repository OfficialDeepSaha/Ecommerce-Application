package com.ECommerce.Project.developBy.DeepSaha;

import java.security.Principal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;












@Controller             
public class ProfileController {
	@Autowired
private	Userservice userservice;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private Userdetailservice userdetailservice;
	
	@Autowired
	private OrderService orderService;
	
	
	
	
	@GetMapping("/my-profile")
	public String myProfile(Model model, Principal principal ) {
		
		
		User user =  userservice.findByEmail(principal.getName());
		
		model.addAttribute("user",user);
		
		
     
		return "myProfile";
		
		}

	
	
	
	@RequestMapping("/my-address")
	public String myAddress(Model model, Principal principal) {
		User user = userservice.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "myAddress";
		
	}
	
	
	
	@RequestMapping(value="/update-user-address", method=RequestMethod.POST)
	public String updateUserAddress(@ModelAttribute("address") Address address, 
			Model model, Principal principal) throws Exception {
		User currentUser = userservice.findByEmail(principal.getName());
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		currentUser.setAddress(address);
		userservice.save(currentUser);
		return "redirect:/my-address";
	}
	
	
	
	
	@RequestMapping(value="/update-user-info", method=RequestMethod.POST)
	public String updateUserInfo( @ModelAttribute("user") User user,
								  @RequestParam("newPassword") String newPassword,
								  Model model, Principal principal  ) throws Exception {
		
		
		
		
		User currentUser = userservice.findByEmail(principal.getName());
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		
		
		User existingUser = userservice.findByEmail(user.getEmail());
		if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
			model.addAttribute("emailExists", true);
			return "myProfile";
		}			
		
	
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setNumber(user.getNumber());
		
		currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
		currentUser.setEmail(user.getEmail());		
		userservice.save(currentUser);
		model.addAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);				
		userdetailservice.loadUserByUsername(currentUser.getEmail());		
		return "myProfile";
	}
	
	
	
	
	@RequestMapping("/my-orders")
	public String myOrders(Model model, Principal principal) {
		User user =userservice.findByEmail(principal.getName());
		model.addAttribute("user", user);
		List<Order> orders = orderService.findByUser(user);
		model.addAttribute("orders", orders);
		return "myOrders";
	}
	
	
	
	
	@RequestMapping("/order-detail")
	public String orderDetail(@RequestParam("order") Long id, Model model) {
		Order order = orderService.findOrderWithDetails(id);
		model.addAttribute("order", order);
		return "orderDetails";
	}
	

}
