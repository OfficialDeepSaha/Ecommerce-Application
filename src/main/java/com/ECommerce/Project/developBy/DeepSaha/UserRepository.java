package com.ECommerce.Project.developBy.DeepSaha;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	
	User findByEmail(String email);

	User findByResetPasswordToken(String token);
}
