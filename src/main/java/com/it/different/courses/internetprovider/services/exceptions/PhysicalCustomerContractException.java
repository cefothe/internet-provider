package com.it.different.courses.internetprovider.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PhysicalCustomerContractException extends RuntimeException {
	public PhysicalCustomerContractException(String message) {
		super(message);
	}

	public PhysicalCustomerContractException() {
	}
}
