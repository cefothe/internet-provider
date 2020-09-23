package com.it.different.courses.internetprovider.persistence.repository;

import com.it.different.courses.internetprovider.persistence.entity.Customer;
import com.it.different.courses.internetprovider.persistence.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
