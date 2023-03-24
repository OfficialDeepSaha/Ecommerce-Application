package com.ECommerce.Project.developBy.DeepSaha;




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="first_name" , nullable=false)
	private String firstName;
	@Column(name="last_name" , nullable=false)
	private String lastName;
	@Column(unique=true, nullable=false)
	private String email;
	private String password;
	@Column(name="phone_number")
	private String number;
	
	@ManyToMany(cascade = CascadeType.ALL , fetch= FetchType.EAGER)
	@JoinTable(name="user_role" ,
    joinColumns = @JoinColumn(name="USER_ID", referencedColumnName="ID"),
       inverseJoinColumns= @JoinColumn (name="ROLE_ID",referencedColumnName= "ID" ))
	private List<Role> roles;
	
	@Column(name = "reset_password_token")
    private String resetPasswordToken;
	
	@OneToOne(cascade= CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="address_id")
	private Address address;


	public User() {
		
	}


	public User(Long id, String firstName, String lastName, String email, String password, List<Role> roles,
			String resetPasswordToken, Address address , String number) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.resetPasswordToken = resetPasswordToken;
		this.address = address;
		this.number = number;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public String getResetPasswordToken() {
		return resetPasswordToken;
	}


	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", number=" + number + ", roles=" + roles + ", resetPasswordToken="
				+ resetPasswordToken + ", address=" + address + "]";
	}


	

	

	

	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
