package com.it.different.courses.internetprovider.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.it.different.courses.internetprovider.persistence.entity.Contract;
import com.it.different.courses.internetprovider.persistence.entity.Contract.ContractStatus;
import com.it.different.courses.internetprovider.persistence.entity.Customer;
import com.it.different.courses.internetprovider.persistence.entity.PhysicalCustomer;
import com.it.different.courses.internetprovider.persistence.entity.Product;
import com.it.different.courses.internetprovider.persistence.entity.Product.ProductStatus;
import com.it.different.courses.internetprovider.persistence.repository.ContractRepository;
import com.it.different.courses.internetprovider.persistence.repository.CustomerRepository;
import com.it.different.courses.internetprovider.persistence.repository.ProductRepository;
import com.it.different.courses.internetprovider.services.dto.ContractDTO;
import com.it.different.courses.internetprovider.services.dto.ContractInformationDTO;
import com.it.different.courses.internetprovider.services.exceptions.PhysicalCustomerContractException;
import com.it.different.courses.internetprovider.services.exceptions.ResourceNotFound;
import com.it.different.courses.internetprovider.services.mapper.ContractInformationDTOMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContractService {

	private static final Logger LOG = LoggerFactory.getLogger(ContractService.class);

	private final ContractRepository contractRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;
	private final AuthenticationFacade authenticationFacade;

	public ContractInformationDTO create(ContractDTO contractDTO) {
		LOG.info("Start creating contract for customer with id {} and product {}", contractDTO.getCustomerId(), contractDTO.getProductId());
		Product product = productRepository.findById(contractDTO.getProductId())
				.orElseThrow(() -> new ResourceNotFound(String.format("Product with Id %d doesn't exist", contractDTO.getProductId())));

		if (product.getStatus() == ProductStatus.DELETED) {
			throw new ResourceNotFound(String.format("Product with Id %d doesn't exist", contractDTO.getProductId()));
		}

		Customer customer = customerRepository.findById(contractDTO.getCustomerId())
				.orElseThrow(() -> new ResourceNotFound(String.format("Customer with Id %d doesn't exist", contractDTO.getCustomerId())));

		validateCustomerContractRule(customer);

		Contract contract = contractRepository.save(new Contract(customer,
				product,
				Instant.now(),
				contractDTO.getMonth(),
				authenticationFacade.getAuthentication(), ContractStatus.ACTIVE));
		LOG.info("Created contract with id {} for customer with id {} and product {}",contract.getId(), contractDTO.getCustomerId(), contractDTO.getProductId());
		return ContractInformationDTOMapper.F.map(contract,customer,product);
	}

	public List<ContractInformationDTO> findAll() {
		return contractRepository.findAll()
				.stream()
				.map(contract -> {
					Customer customer = contract.getCustomer();
					Product product = contract.getProduct();
					return ContractInformationDTOMapper.F.map(contract,customer,product);
				})
				.collect(Collectors.toList());
	}

	@Scheduled(cron = "${contract.ending}")
	public void contractEnding(){
		contractRepository.findAllByStatus(ContractStatus.ACTIVE)
				.forEach(contract -> {
					if(contract.getCreatedAt().plus(contract.getLength(), ChronoUnit.MONTHS).isBefore(Instant.now())){
						contract.ended();
						contractRepository.save(contract);
					}
				});
	}

	private void validateCustomerContractRule(Customer customer){
		if(customer instanceof PhysicalCustomer && contractRepository.findByCustomer(customer).size() == 1){
			throw new PhysicalCustomerContractException();
		}
	}
}
