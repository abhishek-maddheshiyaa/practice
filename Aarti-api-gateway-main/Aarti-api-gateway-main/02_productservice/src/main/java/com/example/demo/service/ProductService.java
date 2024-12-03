package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import jakarta.validation.Valid;
@Service
public class ProductService implements IProductService{
	//for getting log in console
	private final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public List<Product> getProductsFromDatabase() {
		logger.info("Fetching all records from Database...");
		return productRepo.findAll();
	}
	
	@Override
	public Optional<Product> getProductById(int id) {
		
		return productRepo.findById(id);
	}

	@Override
	public void deleteProductById(int id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
		
	}

	@Override
	public Product createProduct(@Valid Product newProduct) {
		// TODO Auto-generated method stub
		return productRepo.save(newProduct);
	}

	@Override
	public ResponseEntity<Product> updateProduct(Integer productId, @Valid Product newProduct) {
		Optional<Product> existingProduct = productRepo.findById(productId);
		existingProduct.get().setPname(newProduct.getPname());
		existingProduct.get().setPrice(newProduct.getPrice());
		productRepo.save(existingProduct.get());
		return ResponseEntity.ok(existingProduct.get());
	}
	
	
	
	
	

}
