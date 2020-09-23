package com.it.different.courses.internetprovider.services.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContractInformationDTO {

	private Long id;

	private CustomerDTO customerDTO;

	private ProductDTO productDTO;

	private Integer month;
}
