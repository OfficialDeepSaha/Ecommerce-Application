package com.ECommerce.Project.developBy.DeepSaha;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




public class CustomUserDetail  implements UserDetails {

	
	
	
	
	
	private User user;
	
	
	
	

	

	

	public CustomUserDetail(User user) {
		super();
		this.user = user;
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList = new ArrayList<>();
		
		user.getRoles().forEach(role -> {
			authorityList.add(new SimpleGrantedAuthority(role.getName()));
			
			
		});
		
		return authorityList;
	}
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}
	
	

	public String getfirstName() {
		
		return user.getFirstName();
	}

	
public String getlastName() {
		
		return user.getLastName();
	}





public String getphonenumber() {
	
	return user.getNumber();
}



public String getfullName() {
	
	
	return user.getFirstName() +""+ user.getLastName();
}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
