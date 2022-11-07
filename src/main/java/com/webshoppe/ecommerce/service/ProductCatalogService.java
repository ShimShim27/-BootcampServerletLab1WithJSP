package com.webshoppe.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import com.webshoppe.ecommerce.entity.Product;
import com.webshoppe.ecommerce.exception.DataAccessException;
import com.webshoppe.ecommerce.exception.ServiceException;
import com.webshoppe.ecommerce.repository.ProductRepository;

public class ProductCatalogService {
	private ProductRepository repository;

	public ProductCatalogService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> getCatalog() {
		try {
			return (List<Product>) repository.findAll();
		} catch (DataAccessException e) {
			throw ServiceException.instance(e.getMessage());
		}

	}

	public List<Product> getProductCatalog(BigDecimal minimumPrice, BigDecimal maximumPrice) {
		try {
			return repository.findByPrice(minimumPrice, maximumPrice);
		} catch (DataAccessException e) {
			throw ServiceException.instance(e.getMessage());
		}

	}
}
