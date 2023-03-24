package com.ECommerce.Project.developBy.DeepSaha;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private Userservice userservice;
	@Autowired
	  private ProductRepository productRepo;
	
	@Autowired
	private OrderService OrderService;
	
	@Autowired
	private OrderRepository orderRepo;
	
	
	@GetMapping("/Super_admin_Deepsaha")
	public String adminModule(Model model) {
		model.addAttribute("countuser" , userRepo.count());
		model.addAttribute("countproduct", productRepo.count() );
		model.addAttribute("countOrder",orderRepo.count());
		
		return "adminHome";
		
	}
	
	@GetMapping("/Super_admin_Deepsaha/users-info")
	public String showUserDetails(Model model) {
		Iterable<User> userdetail = userservice.getAllUsers();
		model.addAttribute("users", userdetail);
		
		return "UsersInfo";
	}

	@GetMapping("/Super_admin_Deepsaha/users-info/{id}")
	public String deleteUserList(@PathVariable("id") Long id) {
		
		userservice.deleteUser_info(id);
		return "redirect:/Super_admin_Deepsaha/users-info";
		
	}
	
	
	
	@GetMapping("/Super_admin_Deepsaha/orders-info")
	public String showUserOrders(Model model, @ModelAttribute("filters") OrderFilterForm filters ) {
		Iterable<Order> orderdetail = OrderService.getallorders();
		model.addAttribute("orders", orderdetail);
		List<Order> pageresult = OrderService.findArticlesByCriteria( filters.getSearch());	
		model.addAttribute("search", pageresult );
		
		return "OrdersInfo";
	}
	
	@GetMapping("/Super_admin_Deepsaha/orders-status{id}")
	public String updateOrderStatus(@PathVariable Long id , Model model ) {
		model.addAttribute("orders", OrderService.getorderById(id));
		
		return "update_orderstatus";
		
	}
	
	
	
	@PostMapping("/Super_admin_Deepsaha/orders-status{id}")
	public String updatedorderstatus(@PathVariable Long id , Model model, @ModelAttribute("orders") Order order) {
		Order orderstatus = OrderService.getorderById(id);
		
		orderstatus.setOrderStatus(order.getOrderStatus());
		OrderService.updatestatus(orderstatus);
		return "redirect:/Super_admin_Deepsaha/orders-info";
	}
	
	
	
	@GetMapping("/Super_admin_Deepsaha/shipment-date{id}")
	public String updateShippingDate(@PathVariable Long id , Model model ) {
		model.addAttribute("update_shipment_date", OrderService.getorderById(id));
		
		return "update_shipment_Date";
		
	}
	
	
	@PostMapping("/Super_admin_Deepsaha/shipment-date{id}")
	public String updatedShippingDate(@PathVariable Long id , Model model, @ModelAttribute("update_shipment_date") Order order ) {
		Order shipmentDate = OrderService.getorderById(id);
		shipmentDate.setShippingDate(order.getShippingDate());
		
		OrderService.updateshipment(shipmentDate);
		
		return "redirect:/Super_admin_Deepsaha/orders-info";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/track_order")
	public String trackMy_Order() {
//		model.addAttribute("track", OrderService.getorderById(id));
		return "track_order";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
