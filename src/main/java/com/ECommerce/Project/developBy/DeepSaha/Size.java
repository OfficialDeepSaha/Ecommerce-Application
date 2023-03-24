package com.ECommerce.Project.developBy.DeepSaha;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Size implements Comparable<Size>{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String value;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product products;

	public Size() {
		
	}

	public Size(String value, Product products) {
		super();
		
		this.value = value;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	@Override
	public int compareTo(Size s) {
		
		return this.value.compareTo(s.getValue());
	}
	
	

}
