package com.it.different.courses.internetprovider.persistence.repository;

import java.util.List;

import com.it.different.courses.internetprovider.persistence.entity.Contract;
import com.it.different.courses.internetprovider.persistence.entity.Contract.ContractStatus;
import com.it.different.courses.internetprovider.persistence.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
	List<Contract> findByCustomer(Customer customer);
	List<Contract> findAllByStatus(ContractStatus contractStatus);
}
