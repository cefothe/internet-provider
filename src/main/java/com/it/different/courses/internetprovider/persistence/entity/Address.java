package com.it.different.courses.internetprovider.persistence.entity;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Address extends BaseEntity {
	private String city;
	private String street;
	private String country;
}
