package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feign.TestFeignClient;
import com.example.model.FeignProduct;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestFeignClient testFeign;

    private static final String TEST_SERVICE = "TestService";
    private int attempt = 1;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World, Spring Boot.... Welcome to You!";
    }

    @GetMapping("/allproducts")
    @CircuitBreaker(name = TEST_SERVICE, fallbackMethod = "getAllProductsFallback")
    @Retry(name = TEST_SERVICE, fallbackMethod = "getAllProductsFallback")
    public ResponseEntity<List<FeignProduct>> getProducts() {
        System.out.println("Retry method called " + attempt++ + " times " + " at " + new Date());
        List<FeignProduct> products = testFeign.productResponse();
        return ResponseEntity.ok().body(products);
    }

   public ResponseEntity<List<FeignProduct>> getAllProductsFallback(Exception throwable) {
        // Logging the error for debugging purposes
    	System.out.println("Fallback method called due to: " + throwable.getMessage());
        throwable.printStackTrace();
        return ResponseEntity.ok().body(testFeign.productResponse());
    }
}

//http://localhost:9198/api/products
//http://localhost:9200/api/allproducts
//http://localhost:9200/actuator/health
