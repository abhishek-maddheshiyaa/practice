package com.example.feign;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.FeignProduct;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient (name = "testFeignClient", url = "${PRODUCT_SERVICE:http://localhost:9198}",fallback = TestFeignClientFallback.class)

public interface TestFeignClient {
	
	@GetMapping("/api/products")	
	List<FeignProduct> productResponse();
	
	 @RequestMapping(method = RequestMethod.GET, value = "/hellonotfound")
	    String getException();


}


