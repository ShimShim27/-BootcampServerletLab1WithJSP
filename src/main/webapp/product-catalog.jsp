<%@page import="com.webshoppe.ecommerce.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	List<Product> products = (List<Product>) request.getAttribute("products");
%>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Product Catalog</title>
	</head>
	
	<body>
	
		<% if(products.isEmpty()) { %>
            <b><%= request.getAttribute("productsEmptyMessage")%></b>
        <% } else { %> 
        
			<table class='table'>
				<thead>
					 <tr>
		                <td>ID</td>
		                <td>Name</td>
		                <td>Description</td>
		                <td>Price</td>
	                </tr>
	           
				</thead>
				<tbody>
					<% for(Product product: products) { %>
	                	 <tr>
			                <td> <%= product.getId() %> </td>
			                <td> <%= product.getName() %> </td>
			                <td> <%= product.getDescription() %> </td>
			                <td> <%= product.getPrice() %> </td>
		                </tr>
	                
	                <% } %>
				</tbody>
				 
			</table>
		
		<% } %>
	
	</body>
</html>