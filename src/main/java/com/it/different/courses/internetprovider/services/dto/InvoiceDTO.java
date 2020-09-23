package com.it.different.courses.internetprovider.services.dto;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class InvoiceDTO {

	private Long id;
	private Long productId;
	private Long contractId;
	private double amount;
	private Instant createdAt;
}
