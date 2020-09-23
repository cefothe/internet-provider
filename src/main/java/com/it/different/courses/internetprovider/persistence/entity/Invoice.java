package com.it.different.courses.internetprovider.persistence.entity;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

	private double amount;

	@OneToOne
	private Contract contract;

	@CreationTimestamp
	private Timestamp createdAt;

	private Instant paidAt;

	private InvoiceStatus status = InvoiceStatus.CREATED;

	public Invoice(double amount, Contract contract){
		this.amount = amount;
		this.contract = contract;
	}

	public void paid(){
		this.status = InvoiceStatus.PAID;
		paidAt = Instant.now();
	}

	public void unpaid(){
		this.status = InvoiceStatus.UNPAID;
	}

	public enum  InvoiceStatus{
		CREATED, PAID, UNPAID
	}
}
