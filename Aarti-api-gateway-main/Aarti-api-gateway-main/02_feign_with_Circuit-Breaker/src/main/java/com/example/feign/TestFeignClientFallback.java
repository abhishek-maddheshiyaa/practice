package com.example.feign;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.example.model.FeignProduct;

@Component
public class TestFeignClientFallback implements TestFeignClient {

	@Override
    public List<FeignProduct> productResponse() {
        return Stream.of(
                new FeignProduct(119, "LED TV", 45000),
                new FeignProduct(345, "Headset", 7000),
                new FeignProduct(475, "Sound bar", 13000),
                new FeignProduct(574, "Puma Shoes", 4600),
                new FeignProduct(678, "Vegetable chopper", 999),
                new FeignProduct(532, "Oven Gloves", 745)
        ).collect(Collectors.toList());
    }
	
	@Override
    public String getException() {
        return "Fixed response";
    }
}