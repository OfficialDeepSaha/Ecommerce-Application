package com.ECommerce.Project.developBy.DeepSaha;

import java.util.List;

public class ProductDTO {
	
	private String title;
	private int stock;	
	private double price;
	private String imageName;
	private List<String> sizes;
	private List<String> categories;
	private List<String> brands;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public List<String> getSizes() {
		return sizes;
	}
	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<String> getBrands() {
		return brands;
	}
	public void setBrands(List<String> brands) {
		this.brands = brands;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ProductDTO [title=" + title + ", stock=" + stock + ", price=" + price + ", imageName=" + imageName
				+ ", sizes=" + sizes + ", categories=" + categories + ", brands=" + brands + ", description="
				+ description + "]";
	}
	
	
	
	
	
	
	
	

}
