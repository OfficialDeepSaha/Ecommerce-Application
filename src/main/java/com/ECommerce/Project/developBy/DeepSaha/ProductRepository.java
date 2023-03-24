package com.ECommerce.Project.developBy.DeepSaha;

import java.util.List;

import java.util.Optional;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;






@Repository
public interface ProductRepository extends CrudRepository<Product, Long> , JpaSpecificationExecutor<Product> {

	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	Optional<Product> findById(Long id);
	
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	List<Product> findAll();
	
	@Query("SELECT DISTINCT s.value FROM Size s")
	List<String> findAllSizes();
	
	@Query("SELECT DISTINCT c.name FROM Category c")
	List<String> findAllCategories();
	
	@Query("SELECT DISTINCT b.name FROM Brand b")
	List<String> findAllBrands();

	
	
	
	
	
	
}
