package com.it.different.courses.internetprovider.persistence.repository;

import java.util.List;

import com.it.different.courses.internetprovider.persistence.entity.Invoice;
import com.it.different.courses.internetprovider.persistence.entity.Invoice.InvoiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	List<Invoice> findAllByStatus(InvoiceStatus invoiceStatus);
}
