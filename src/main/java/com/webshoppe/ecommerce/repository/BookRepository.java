package com.webshoppe.ecommerce.repository;

import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class BookRepository extends ProductRepository {
	private static String TABLE_NAME = "BooksDetails";
	private static String ID_COL = "bid";
	private static String NAME_COL = "title";
	private static String DESCRIPTION_COL = "bookdesc";
	private static String PRICE_COL = "bookprice";

	public BookRepository(JdbcConnectionManager jdbcConnectionManager) {
		super(jdbcConnectionManager, TABLE_NAME, ID_COL, NAME_COL, DESCRIPTION_COL, PRICE_COL);

	}

}
