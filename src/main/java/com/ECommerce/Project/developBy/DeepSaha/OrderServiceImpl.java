package com.ECommerce.Project.developBy.DeepSaha;

import java.time.LocalDate;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private JavaMailSender emailSender;
	
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	ProductRepository productRepository;
			
	@Override
	@Transactional
	@CacheEvict(value = "itemcount", allEntries = true)
	public synchronized Order createOrder(ShoppingCart shoppingCart, Shipping shipping, Payment payment, User user) {
		Order order = new Order();
		order.setUser(user);
		order.setPayment(payment);
		order.setShipping(shipping);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shipping.setOrder(order);
		payment.setOrder(order);			
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);				
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("In Progress");
		
		order = orderRepository.save(order);
		
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Product product = item.getProduct();
			product.decreaseStock(item.getQty());
			productRepository.save(product);
			item.setOrder(order);
			cartItemRepository.save(item);
		}		
		return order;	
	}
	
	@Override
	public Order findOrderWithDetails(Long id) {
		return orderRepository.findEagerById(id);
	}	

	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

	@Override
	public Iterable<Order> getallorders() {
		
		return orderRepository.findAll();
	}

	

	@Override
	public Order getorderById(Long id) {
		
		return orderRepository.findEagerById(id);
	}

	@Override
	public void updatestatus(Order order) {
		
		orderRepository.save(order);
		
	}

	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Environment env;
	
	@Override
	public void sendPdfEmail(String name, String email, Long id, byte[] bytes) {
		
		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			String content = "Hi, <b>" + name + "</b> Thank you for your order.  Order Details.<br>";
			helper.setSubject("D-cart Store: ORD" + id);
			helper.setText(content, true);
			helper.setTo(email);
			helper.addCc(env.getProperty("mailToCc"));
			helper.setFrom(env.getProperty("spring.mail.username"));
			String fileName = "ORD_"+id+".pdf";
			helper.addAttachment(fileName, new ByteArrayResource(bytes));
			emailSender.send(message);
			log.info("Email Sent With File : "+fileName);
		} catch (MessagingException e) {
			log.info("Exception Occurred While Sendimg Email.");
			throw new MailParseException(e);
		}	
		
		
		
		
	
		
	}

	@Override
	public void updateshipment(Order shipmentDate) {
		
		orderRepository.save(shipmentDate);
		
		
	}

	@Override
	public List<Order> findArticlesByCriteria( String search) {
		List<Order> page =  orderRepository.findAll(OrderSpecification.filterBy(search));
        return page;		
	}
	
	
	
	
	
	
	
	
	
	

}
