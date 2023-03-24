package com.ECommerce.Project.developBy.DeepSaha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String ShopPage() {
		
		return"index";
		
	}
	
	

}
