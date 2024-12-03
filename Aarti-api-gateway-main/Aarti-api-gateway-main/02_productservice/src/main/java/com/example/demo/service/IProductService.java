package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Product;

import jakarta.validation.Valid;

public interface IProductService {
	
	List<Product> getProductsFromDatabase();
	public Optional<Product> getProductById(int id);
	void deleteProductById(int id);
	Product createProduct(@Valid Product newProduct);
	//ResponseEntity<Product> updateProduct(Integer productId);
	ResponseEntity<Product> updateProduct(Integer productId, @Valid Product newProduct);
	

}
