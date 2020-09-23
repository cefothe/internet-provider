package com.it.different.courses.internetprovider.services.mapper;

import com.it.different.courses.internetprovider.persistence.entity.Product;
import com.it.different.courses.internetprovider.services.dto.ProductDTO;

public enum ProductDTOMapper {
	F;
	public ProductDTO map(Product product){
		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getFee(),
				product.getBandwidth(),
				product.getStatus().name());
	}
}
