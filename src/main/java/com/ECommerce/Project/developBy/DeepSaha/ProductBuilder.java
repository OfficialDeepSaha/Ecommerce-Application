package com.ECommerce.Project.developBy.DeepSaha;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




public class ProductBuilder {
	private String title;
	private int stock;	
	private double price;
	private String imageName;
	private List<String> sizes;
	private List<String> categories;
	private List<String> brands;
	private String description;
	
	
	

	
	
	


	
	
	public ProductBuilder() {
		
	}

	public ProductBuilder withdescription(String description) {
		this.description = description;
		return this;
	}


	public ProductBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public ProductBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public ProductBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public ProductBuilder sizesAvailable(List<String> sizes) {
		this.sizes = sizes;
		return this;
	}
	
	public ProductBuilder ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}
	
	public ProductBuilder ofBrand(List<String> brands) {
		this.brands = brands;
		return this;
	}

	
	
	public ProductBuilder uploadimage(String imageName) {
		this.imageName = imageName;
		return this;
	}
	
	
	

	
	public Product build(){
		
		Product product = new Product();
		product.setTitle(this.title);
		product.setPrice(this.price);
		product.setStock(this.stock);
		product.setImageName(this.imageName);
		product.setDescription(this.description);

		if (this.sizes != null && !this.sizes.isEmpty()) {
			Set<Size> sizeElements = new HashSet<>();
			for (String val : this.sizes) {
				sizeElements.add(new Size( val,product));
			}	
			product.setSizes(sizeElements);
		}
		
		if (this.categories != null && !this.categories.isEmpty() ) {
			Set<Category> catElements = new HashSet<>();
			for (String val : this.categories) {
				catElements.add(new Category(val,product));
			}
			product.setCategories(catElements);
		}		
		if (this.brands != null && !this.brands.isEmpty() ) {
			Set<Brand> brandlements = new HashSet<>();
			for (String val : this.brands) {
				brandlements.add(new Brand(val,product));
			}
			product.setBrands(brandlements);
		}		
		
		
		return product;
	}




	



	



	
	
	
	
}
