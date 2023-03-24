package com.ECommerce.Project.developBy.DeepSaha;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String streetAddress;
	private String city;
	private String country;
	private String zipCode;
	public Address(Long id, String streetAddress, String city, String country, String zipCode) {
		super();
		this.id = id;
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}
	
	
	
	
	public Address() {
		
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", streetAddress=" + streetAddress + ", city=" + city + ", country=" + country
				+ ", zipCode=" + zipCode + "]";
	}
	
	
	
	
	
}
