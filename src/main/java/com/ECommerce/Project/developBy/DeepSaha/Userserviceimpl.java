package com.ECommerce.Project.developBy.DeepSaha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userserviceimpl implements Userservice {

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
    private	UserRepository userRepo;
	
	
	
	
	@Override
	public User registeruser(User user) {
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		
		return userRepo.save(user);
	}




	@Override
	public User findByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}




	@Override
	public User save(User user) {
	return	userRepo.save(user);
		
	}




	@Override
	public Iterable<User> getAllUsers() {
		
		return userRepo.findAll();
	}




	@Override
	public void deleteUser_info(Long id) {
		
		userRepo.deleteById(id);
	}

}
