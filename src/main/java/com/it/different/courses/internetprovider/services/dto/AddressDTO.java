package com.it.different.courses.internetprovider.services.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressDTO {
	private String city;
	private String street;
	private String country;
}
