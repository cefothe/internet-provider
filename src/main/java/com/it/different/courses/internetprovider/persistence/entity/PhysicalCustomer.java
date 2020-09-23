package com.it.different.courses.internetprovider.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PhysicalCustomer extends Customer{

	private String firstName;
	private String lastName;
	@OneToOne
	private Address address;

	@Builder
	protected PhysicalCustomer(User user, String firstName, String lastName, Address address) {
		super(user);
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
}
