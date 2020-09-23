package com.it.different.courses.internetprovider.services.mapper;

import com.it.different.courses.internetprovider.persistence.entity.Customer;
import com.it.different.courses.internetprovider.persistence.entity.LegalCustomer;
import com.it.different.courses.internetprovider.persistence.entity.PhysicalCustomer;
import com.it.different.courses.internetprovider.services.dto.ClientType;
import com.it.different.courses.internetprovider.services.dto.CustomerDTO;

public enum  CustomerDTOMapper {
	F;

	public CustomerDTO map(Customer customer){
		if(customer instanceof LegalCustomer){
			var legalCustomer = (LegalCustomer) customer;
			return CustomerDTO.builder()
					.clientType(ClientType.LEGAL)
					.companyName(legalCustomer.getCompanyName())
					.responsiblePerson(legalCustomer.getResponsiblePerson())
					.vatNumber(legalCustomer.getVatNumber()).build();
		}
		if(customer instanceof PhysicalCustomer){
			var physicalCustomer = (PhysicalCustomer) customer;
			return CustomerDTO.builder()
					.clientType(ClientType.PHYSICAL)
					.firstName(physicalCustomer.getFirstName())
					.lastName(physicalCustomer.getLastName())
					.build();
		}
		return null;
	}
}
