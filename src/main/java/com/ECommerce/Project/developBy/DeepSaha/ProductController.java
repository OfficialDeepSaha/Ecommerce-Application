package com.ECommerce.Project.developBy.DeepSaha;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;







@Controller
public class ProductController {
	
	
@Autowired
private	ProductService productService;

public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@GetMapping("/Super_admin_Deepsaha/products")
	public String getallproducts(Model model) {
		Iterable<Product> products = productService.getallproducts();
		model.addAttribute("products", products );
		return "products";
		
		
	}
	
	
	@GetMapping("/Super_admin_Deepsaha/products/add")
	public String addproduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("allSizes", productService.getAllSizes());
		model.addAttribute("allBrands", productService.getAllBrands());
		model.addAttribute("allCategories", productService.getAllCategories());
		return "ProductAdd";
	}
	
	
	
	@PostMapping("/Super_admin_Deepsaha/products/add")
	public String processing_product_add(@ModelAttribute("product") Product product, HttpServletRequest request  )   {
		
		
		Product newArticle = new ProductBuilder()
				.withTitle(product.getTitle())
				.stockAvailable(product.getStock())
				.withPrice(product.getPrice())
				.uploadimage(product.getImageName())
				.withdescription(product.getDescription())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.build();		
		
		
		
				
				
		
		productService.addproduct(newArticle);
		
		
		
		return "redirect:/Super_admin_Deepsaha/products";  
			}
	
	
	
	
	@GetMapping("/Super_admin_Deepsaha/products/{id}")
	public String deleteProduct(@PathVariable Long id) {
		
		productService.deleteproductById(id);
		return "redirect:/Super_admin_Deepsaha/products";
	}
	
	
	
	
	
	

}
