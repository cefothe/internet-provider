package com.it.different.courses.internetprovider.services.exceptions;

public class ClientNotFoundException extends RuntimeException{

	public ClientNotFoundException(String message){
		super(message);
	}

	public ClientNotFoundException() {
	}
}

