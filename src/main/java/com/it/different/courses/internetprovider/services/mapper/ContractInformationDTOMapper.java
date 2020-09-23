package com.it.different.courses.internetprovider.services.mapper;

import com.it.different.courses.internetprovider.persistence.entity.Contract;
import com.it.different.courses.internetprovider.persistence.entity.Customer;
import com.it.different.courses.internetprovider.persistence.entity.Product;
import com.it.different.courses.internetprovider.services.dto.ContractInformationDTO;

public enum  ContractInformationDTOMapper{
	F;
	public ContractInformationDTO map(Contract contract, Customer customer, Product product) {
		return new ContractInformationDTO(contract.getId(), CustomerDTOMapper.F.map(customer),
				ProductDTOMapper.F.map(product), contract.getLength());
	}
}
