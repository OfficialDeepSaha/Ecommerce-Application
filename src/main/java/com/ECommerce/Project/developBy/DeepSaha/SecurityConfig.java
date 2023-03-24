package com.ECommerce.Project.developBy.DeepSaha;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
   @Bean
  public Userdetailservice userdetailservice() {
	   
	   return new Userdetailservice();
	   
   }
	  
	
		
		
	
	
	
	
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/Super_admin_Deepsaha/**").hasRole("ADMIN")
		.antMatchers("/**" , "/store/**").permitAll()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login").usernameParameter("email")
		.passwordParameter("password").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login").deleteCookies("JSESSIONID").permitAll().and().exceptionHandling().and().csrf().disable();
			
		http.headers().frameOptions().disable();
	}



	public DaoAuthenticationProvider getdaoAuthenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userdetailservice());
		daoAuthenticationProvider.setPasswordEncoder(bcryptPasswordEncoder());
		return daoAuthenticationProvider;
		
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getdaoAuthenticationProvider());
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/resources/**", "/static/**","/images/**","/productImages/**","/css/**","/js/**");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
