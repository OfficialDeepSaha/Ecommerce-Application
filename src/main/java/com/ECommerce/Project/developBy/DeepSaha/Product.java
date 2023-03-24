package com.ECommerce.Project.developBy.DeepSaha;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private int stock;
	private double price;
	private String imageName;
	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Size> sizes;

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Brand> brands;

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Category> categories;
	
	

	public Product() {

	}

	public Product(Long id, String title, String description, int stock, double price, Set<Size> sizes,
			Set<Brand> brands, Set<Category> categories, String imageName) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.sizes = sizes;
		this.brands = brands;
		this.categories = categories;
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	
	
	
	

	

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", stock=" + stock
				+ ", price=" + price + ", imageName=" + imageName + ", sizes=" + sizes + ", brands=" + brands
				+ ", categories=" + categories + "]";
	}

	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}

	public void decreaseStock(int amount) {
		this.stock -= amount;
	}

	public void addsize(Size size) {
		sizes.add(size);
		size.setProducts(this);

	}

	public void removesize(Size size) {
		sizes.remove(size);
		size.setProducts(null);

	}

	public void addbrand(Brand brand) {
		brands.add(brand);
		brand.setProducts(this);

	}

	public void removebrand(Brand brand) {
		brands.remove(brand);
		brand.setProducts(null);

	}

	public void addcategory(Category category) {
		categories.add(category);
		category.setProducts(this);
	}

	public void removecategory(Category category) {
		categories.remove(category);
		category.setProducts(null);
	}

	
	
	
	
	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
