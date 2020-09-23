package com.it.different.courses.internetprovider.controller;

import java.util.List;

import com.it.different.courses.internetprovider.services.InvoiceService;
import com.it.different.courses.internetprovider.services.dto.InvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
@RestController
public class InvoiceController {

	private final InvoiceService invoiceService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/")
	@Operation(summary = "get all invoices", security = @SecurityRequirement(name = "bearerAuth"))
	public List<InvoiceDTO> getAll(){
		return invoiceService.getAll();
	}

	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@PostMapping("/{invoiceId}/pay")
	@Operation(summary = "pay invoice", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Void> payInvoice(@PathVariable("invoiceId") Long invoiceId){
		invoiceService.pay(invoiceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}



}
