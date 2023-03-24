package com.ECommerce.Project.developBy.DeepSaha;




import java.util.List;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;





@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
//	@Value("${productService.featured-items-number}")
//	private int featuredProductsNumber;
//	
	
	
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	
	
	
	
	
	@Override
	public Iterable<Product> getallproducts() {
		
		return productRepo.findAll();
	}


	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public Product addproduct(Product product){
		return productRepo.save(product);
	}


	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public void deleteproductById(Long id) {
		productRepo.deleteById(id);
		
	}


	@Override
	public Product findById(Long id) {
		Optional<Product> opt = productRepo.findById(id);
		return opt.get() ;
	}


	@Override
	public List<String> getAllSizes() {
		
		return productRepo.findAllSizes();
	}


	@Override
	public List<String> getAllCategories() {
		
		return productRepo.findAllCategories();
	}


	@Override
	public List<String> getAllBrands() {
		
		return productRepo.findAllBrands();
	}


	@Override
	public Page<Product> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
			List<String> sizes, List<String> categories, List<String> brands, String search) {
		Page<Product> page = productRepo.findAll(ArticleSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands, search), pageable);
        return page;	
	}



	

	


	


	

}
