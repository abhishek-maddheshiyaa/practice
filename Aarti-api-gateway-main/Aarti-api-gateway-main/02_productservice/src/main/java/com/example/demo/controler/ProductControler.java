package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.IProductService;

import jakarta.validation.Valid;

@RestController // http://localhost:9191/api/hello
@RequestMapping("/api")
public class ProductControler {

	@Autowired
	IProductService productService;

	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}

	@GetMapping("/products")
	List<Product> products() {
		// System.out.println("Running");
		return productService.getProductsFromDatabase();

	}

	// http://localhost:9192/api/products/1
	@GetMapping("/products/{id}")
	Optional<Product> findByProductId(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Product> product = productService.getProductById(id);
		product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return product;
	}

	@DeleteMapping("/products/{id}")
	void deleteProductById(@PathVariable int id) {
		productService.deleteProductById(id);
	}

	@PostMapping("/products")
	public Product createEmployee(@Valid @RequestBody Product newProduct) {
		return productService.createProduct(newProduct);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateEmployee
	(@PathVariable(value = "id") Integer productId,@Valid @RequestBody Product newProduct) {
		return productService.updateProduct(productId,newProduct);
	}

	// http://localhost:9192/api/products/req?id=1
	@GetMapping("/products/req")
	Optional<Product> findByProductIdUsingRequest(@RequestParam int id) {
		
		return productService.getProductById(id);
	}
	
	// http://localhost:9192/api/products/x
	@GetMapping(path = "/products/xml", produces = {MediaType.APPLICATION_XML_VALUE})
	List<Product> productsInXML() {
		// System.out.println("Running");
		return productService.getProductsFromDatabase();

	}
	
	
}
