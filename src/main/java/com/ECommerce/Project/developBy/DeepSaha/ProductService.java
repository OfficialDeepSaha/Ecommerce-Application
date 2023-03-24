package com.ECommerce.Project.developBy.DeepSaha;

import java.util.List;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;





public interface ProductService {

	
	Iterable<Product> getallproducts();
	
	Product addproduct(Product product);
	
	void deleteproductById(Long id);
	
	Product findById(Long id);

	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

	
	Page<Product> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands, String search);
	
	

	
	
}
