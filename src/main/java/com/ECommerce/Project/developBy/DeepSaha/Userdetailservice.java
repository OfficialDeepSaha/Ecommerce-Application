package com.ECommerce.Project.developBy.DeepSaha;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Userdetailservice implements UserDetailsService {

@Autowired
private	UserRepository userRepo;
	
	


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if(user!=null) {
			
			return new CustomUserDetail(user);
			
		}
		
		
		throw new UsernameNotFoundException("USER NOT FOUND!");
		
	}

	
	
	
	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
		  User user = userRepo.findByEmail(email);
		        if (user != null) {
		            user.setResetPasswordToken(token);
		            userRepo.save(user);
		        } else {
		            throw new UsernameNotFoundException("Could not find any customer with the email " + email);
		        }
		    }
		     
		    public User getByResetPasswordToken(String token) {
		        return userRepo.findByResetPasswordToken(token);
		    }
		     
		    public void updatePassword(User user, String newPassword) {
		        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		        String encodedPassword = passwordEncoder.encode(newPassword);
		        user.setPassword(encodedPassword);
		         
		        user.setResetPasswordToken(null);
		        userRepo.save(user);
		    }


	
	
	
	
}
