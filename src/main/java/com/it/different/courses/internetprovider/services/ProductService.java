package com.it.different.courses.internetprovider.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.it.different.courses.internetprovider.persistence.entity.Product;
import com.it.different.courses.internetprovider.persistence.repository.ProductRepository;
import com.it.different.courses.internetprovider.services.dto.ProductDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final AuthenticationFacade authenticationFacade;

	public List<ProductDTO> findAll() {
		return productRepository.findAll()
				.stream()
				.map(this::map)
				.collect(Collectors.toList());
	}

	public ProductDTO create(ProductDTO productDTO) {
		Product product = Product.builder()
				.name(productDTO.getName())
				.bandwidth(productDTO.getBandwidth())
				.fee(productDTO.getFee())
				.createdBy(authenticationFacade.getAuthentication())
				.build();
		return Optional.of(productRepository.save(product)).map(
				this::map
		).orElseThrow(RuntimeException::new);
	}

	public ProductDTO map(Product product) {
		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getFee(),
				product.getBandwidth(),
				product.getStatus().name());
	}
}
