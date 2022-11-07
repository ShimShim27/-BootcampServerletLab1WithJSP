package com.webshoppe.ecommerce.repository;

import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class ToyRepository extends ProductRepository {

	private static String TABLE_NAME = "ToysDetails";
	private static String ID_COL = "tid";
	private static String NAME_COL = "tname";
	private static String DESCRIPTION_COL = "tdesc";
	private static String PRICE_COL = "tprice";

	public ToyRepository(JdbcConnectionManager jdbcConnectionManager) {
		super(jdbcConnectionManager, TABLE_NAME, ID_COL, NAME_COL, DESCRIPTION_COL, PRICE_COL);

	}

}
