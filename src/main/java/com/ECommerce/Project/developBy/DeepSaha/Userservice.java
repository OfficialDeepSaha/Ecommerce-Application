package com.ECommerce.Project.developBy.DeepSaha;

import java.util.List;

public interface Userservice {

	Iterable<User> getAllUsers();
	
	User registeruser(User user);

	
	User findByEmail(String email);


	User save(User user);
	
	
	void deleteUser_info(Long id);
	
}
