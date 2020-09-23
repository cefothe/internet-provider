package com.it.different.courses.internetprovider.services;

import com.it.different.courses.internetprovider.persistence.entity.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {

	public User getAuthentication() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}