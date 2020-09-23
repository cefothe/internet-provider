package com.it.different.courses.internetprovider.persistence.repository;

import com.it.different.courses.internetprovider.persistence.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
