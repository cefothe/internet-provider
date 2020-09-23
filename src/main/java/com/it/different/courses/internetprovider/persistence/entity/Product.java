package com.it.different.courses.internetprovider.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

	private String name;

	private double fee;

	private Integer bandwidth;

	@CreationTimestamp
	private Timestamp createAt;

	@UpdateTimestamp
	private Timestamp updateAt;

	@OneToOne
	private User createdBy;

	@Enumerated(EnumType.STRING)
	private ProductStatus status = ProductStatus.ACTIVE;

	@Builder
	private Product(String name, double fee, Integer bandwidth, User createdBy) {
		this.name = name;
		this.fee = fee;
		this.bandwidth = bandwidth;
		this.createdBy = createdBy;
	}

	public enum ProductStatus{
		ACTIVE, DELETED
	}
}
