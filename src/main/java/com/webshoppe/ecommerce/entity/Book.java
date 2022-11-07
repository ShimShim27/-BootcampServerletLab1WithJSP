package com.webshoppe.ecommerce.entity;

import java.math.BigDecimal;

public class Book extends Product {
	public Book(String id, String name, String description, BigDecimal price) {
		super(id, name, description, price);
	}
}
