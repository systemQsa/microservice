package com.tech.microservices.product.controller;

import com.tech.microservices.product.dto.ProductRequest;
import com.tech.microservices.product.model.Product;
import com.tech.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}
}
