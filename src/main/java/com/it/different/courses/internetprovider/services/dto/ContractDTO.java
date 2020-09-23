package com.it.different.courses.internetprovider.services.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Valid
public class ContractDTO {

	@Min(1)
	@NotNull
	private Long customerId;

	@Min(1)
	@NotNull
	private Long productId;

	@Min(1)
	@NotNull
	private Integer month;
}
