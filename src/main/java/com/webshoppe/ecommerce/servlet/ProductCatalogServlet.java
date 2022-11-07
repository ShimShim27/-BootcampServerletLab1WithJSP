package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshoppe.ecommerce.entity.Product;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.BookRepository;
import com.webshoppe.ecommerce.repository.FlowerRepository;
import com.webshoppe.ecommerce.repository.ToyRepository;
import com.webshoppe.ecommerce.service.ProductCatalogService;

public class ProductCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_ATTRIBUTE_PRODUCTS = "products";
	private static final String REQUEST_ATTRIBUTE_PRODUCTS_EMPTY_MESSAGE = "productsEmptyMessage";
	private static final String MESSAGE_EMPTY_CATALOG = "Empty Catalog!";
	private static final String MESSAGE_NO_PRODUCT_IN_PRICE_RANGE = "Cannot find products that met the price range.";

	private ProductCatalogService toyCatalogService;
	private ProductCatalogService flowerCatalogService;
	private ProductCatalogService bookCatalogService;

	@Override
	public void init(ServletConfig config) throws ServletException {

		final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
		toyCatalogService = new ProductCatalogService(new ToyRepository(jdbcConnectionManager));
		flowerCatalogService = new ProductCatalogService(new FlowerRepository(jdbcConnectionManager));
		bookCatalogService = new ProductCatalogService(new BookRepository(jdbcConnectionManager));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		final List<Product> products = this.getProductCatalogService(request).getCatalog();
		this.showProducts(products, MESSAGE_EMPTY_CATALOG, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		final String minimumPriceParam = request.getParameter("minimum-price");
		final BigDecimal minimumPrice = new BigDecimal(minimumPriceParam);

		final String maximumPriceParam = request.getParameter("maximum-price");
		final BigDecimal maximumPrice = new BigDecimal(maximumPriceParam);

		final List<Product> products = this.getProductCatalogService(request).getProductCatalog(minimumPrice,
				maximumPrice);

		this.showProducts(products, MESSAGE_NO_PRODUCT_IN_PRICE_RANGE, request, response);
	}

	private ProductCatalogService getProductCatalogService(HttpServletRequest req) {
		ProductCatalogService catalogService = null;
		String category = req.getParameter("category");

		if (category.equals("toys")) {
			catalogService = this.toyCatalogService;
		} else if (category.equals("books")) {
			catalogService = this.bookCatalogService;
		} else if (category.equals("flowers")) {
			catalogService = this.flowerCatalogService;
		}
		return catalogService;

	}

	private void showProducts(List<Product> products, String productsEmptyMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(REQUEST_ATTRIBUTE_PRODUCTS, products);
		request.setAttribute(REQUEST_ATTRIBUTE_PRODUCTS_EMPTY_MESSAGE, productsEmptyMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/product-catalog.jsp");
		dispatcher.forward(request, response);
	}

}
