package com.it.different.courses.internetprovider.controller;

import java.util.List;

import com.it.different.courses.internetprovider.services.ProductService;
import com.it.different.courses.internetprovider.services.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {
	private final ProductService productService;

	@Operation(summary = "get all products", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/")
	public List<ProductDTO> getAll() {
		return productService.findAll();
	}

	@Operation(summary = "create product", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> create(@RequestBody @Validated ProductDTO productDTO) {
		productService.create(productDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
