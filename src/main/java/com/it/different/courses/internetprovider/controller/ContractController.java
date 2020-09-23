package com.it.different.courses.internetprovider.controller;

import java.util.List;

import com.it.different.courses.internetprovider.services.ContractService;
import com.it.different.courses.internetprovider.services.dto.ContractDTO;
import com.it.different.courses.internetprovider.services.dto.ContractInformationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
@RestController
public class ContractController {
	private final ContractService contractService;

	@Operation(summary = "get all contracts", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<ContractInformationDTO> getAll() {
		return contractService.findAll();
	}

	@Operation(summary = "create contract", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ContractInformationDTO> create(@RequestBody ContractDTO contractDTO) {
		return new ResponseEntity<>(contractService.create(contractDTO), HttpStatus.CREATED);
	}
}
