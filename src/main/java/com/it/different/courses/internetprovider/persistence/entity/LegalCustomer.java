package com.it.different.courses.internetprovider.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LegalCustomer extends Customer{
	private String vatNumber;
	private String companyName;
	private String responsiblePerson;
	@OneToMany
	private List<Address> addresses;

	@Builder
	private LegalCustomer(User user, String vatNumber, String companyName, String responsiblePerson, List<Address> addresses) {
		super(user);
		this.vatNumber = vatNumber;
		this.companyName = companyName;
		this.responsiblePerson = responsiblePerson;
		this.addresses = addresses;
	}
}
