package com.it.different.courses.internetprovider.services.dto;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Valid
public class ProductDTO {
	private Long id;

	@NotBlank
	@Size(min = 4, max = 100)
	private String name;

	@Digits(integer = 50, fraction = 2)
	@DecimalMin(value = "0", inclusive = false)
	private double fee;

	@NotNull
	@Min(0)
	@Max(2000)
	private Integer bandwidth;

	private String status;

	public ProductDTO(Long id, String name, double fee, Integer bandwidth, String status) {
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.bandwidth = bandwidth;
		this.status = status;
	}

	public ProductDTO(Long id, String name, double fee, Integer bandwidth) {
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.bandwidth = bandwidth;
	}
}
