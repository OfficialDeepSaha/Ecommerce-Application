package com.ECommerce.Project.developBy.DeepSaha;

import java.util.Date;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
	
	Iterable<Order> getallorders();
	
	
	void sendPdfEmail(String name, String email, Long id, byte[] bytes);
		Order getorderById(Long id);

	void updatestatus(Order order );

	void updateshipment(Order shipmentDate);
	
	
	
	List<Order> findArticlesByCriteria(String search);
	
	
}
