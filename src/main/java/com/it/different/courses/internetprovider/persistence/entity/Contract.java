package com.it.different.courses.internetprovider.persistence.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Product product;

	private Instant createdAt;

	private Integer length;

	@OneToOne
	private User createdBy;

	private ContractStatus status;

	public void suspend(){
		this.status = ContractStatus.SUSPENDED;
	}

	public void ended(){
		this.status = ContractStatus.ENDED;
	}
	public enum ContractStatus{
		ACTIVE, SUSPENDED, ENDED
	}

}