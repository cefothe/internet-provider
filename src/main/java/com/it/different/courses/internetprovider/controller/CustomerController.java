package com.it.different.courses.internetprovider.controller;

import java.util.List;

import com.it.different.courses.internetprovider.persistence.entity.Customer;
import com.it.different.courses.internetprovider.services.CustomerService;
import com.it.different.courses.internetprovider.services.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

	private final CustomerService customerService;

	@Operation(summary = "get all customers", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<CustomerDTO> getAll() {
		return  customerService.getAll();
	}

	@Operation(summary = "Create customers", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Void> create(@RequestBody CustomerDTO customerDTO) {
		customerService.create(customerDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}