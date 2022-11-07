package com.webshoppe.ecommerce.repository;

import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class FlowerRepository extends ProductRepository {

	private static String TABLE_NAME = "FlowersDetails";
	private static String ID_COL = "fid";
	private static String NAME_COL = "fname";
	private static String DESCRIPTION_COL = "fdesc";
	private static String PRICE_COL = "fprice";

	public FlowerRepository(JdbcConnectionManager jdbcConnectionManager) {
		super(jdbcConnectionManager, TABLE_NAME, ID_COL, NAME_COL, DESCRIPTION_COL, PRICE_COL);

	}
}
