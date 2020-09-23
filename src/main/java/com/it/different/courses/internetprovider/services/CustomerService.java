package com.it.different.courses.internetprovider.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.it.different.courses.internetprovider.persistence.entity.Customer;
import com.it.different.courses.internetprovider.persistence.entity.LegalCustomer;
import com.it.different.courses.internetprovider.persistence.entity.PhysicalCustomer;
import com.it.different.courses.internetprovider.persistence.repository.CustomerRepository;
import com.it.different.courses.internetprovider.services.dto.ClientType;
import com.it.different.courses.internetprovider.services.dto.CustomerDTO;
import com.it.different.courses.internetprovider.services.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

	private final AuthenticationFacade authenticationFacade;
	private final CustomerRepository customerRepository;

	public void create(CustomerDTO customerDTO) {
		if(ClientType.LEGAL == customerDTO.getClientType()){
			var legalCustomer = LegalCustomer.builder()
					.companyName(customerDTO.getCompanyName())
					.responsiblePerson(customerDTO.getResponsiblePerson())
					.vatNumber(customerDTO.getVatNumber())
					.user(authenticationFacade.getAuthentication())
					.build();
			customerRepository.save(legalCustomer);
		}
		if(ClientType.PHYSICAL == customerDTO.getClientType()){
			var physicalCustomer = PhysicalCustomer.builder()
					.firstName(customerDTO.getFirstName())
					.lastName(customerDTO.getLastName())
					.user(authenticationFacade.getAuthentication())
					.build();
			customerRepository.save(physicalCustomer);
		}
	}

	public List<CustomerDTO> getAll() {
		return customerRepository.findAll().stream()
				.map(this::map)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	public CustomerDTO map(Customer customer) {
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

	public Pair<ClientType, Customer> findClientAndClientType(Long id){
		var client = customerRepository.findById(id).orElseThrow(ClientNotFoundException::new);
		return  Pair.of(determinateClientType(client), client);
	}

	private ClientType determinateClientType(Customer customer){
		if(customer instanceof LegalCustomer){
			return ClientType.LEGAL;
		}
		return ClientType.PHYSICAL;
	}
}
