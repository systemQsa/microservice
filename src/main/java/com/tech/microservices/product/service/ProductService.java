package com.tech.microservices.product.service;

import com.tech.microservices.product.dto.ProductRequest;
import com.tech.microservices.product.model.Product;
import com.tech.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public Product createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
			.name(productRequest.name())
			.description(productRequest.description())
			.price(productRequest.price())
			.build();

		productRepository.save(product);
		log.info("Product created successfully");

		return product;
	}
}
