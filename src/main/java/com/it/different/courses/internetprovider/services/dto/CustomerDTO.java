package com.it.different.courses.internetprovider.services.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.validation.annotation.Validated;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@Data
public class CustomerDTO {

	@NotNull
	private ClientType clientType;

	private Long id;

	private List<AddressDTO> addresses;

	private String firstName;

	private String lastName;

	private String companyName;

	private String vatNumber;

	private String responsiblePerson;

}