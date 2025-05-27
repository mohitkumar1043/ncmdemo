<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Products</title>
<style>
body {
	background: linear-gradient(135deg, #1e3c72, #2a5298);
}

table {
	border-collapse: collapse;
	width: 90%;
	margin: 20px auto;
}

th, td {
	padding: 12px;
	border: 1px solid #ccc;
	text-align: center;
}

img {
	width: 100px;
	height: auto;
}

.remove-button {
	background-color: #ff4d4d;
	color: white;
	border: none;
	padding: 6px 12px;
	cursor: pointer;
	border-radius: 5px;
}
</style>
</head>
<body>

	<h2 style="text-align: center;">📦 Product List</h2>
	<!-- Flash messages -->
	<c:if test="${not empty msg}">
		<div
			style="color: green; font-weight: bold; text-align: center; margin-bottom: 10px;">
			${msg}</div>
	</c:if>

	<c:if test="${not empty error}">
		<div
			style="color: red; font-weight: bold; text-align: center; margin-bottom: 10px;">
			${error}</div>
	</c:if>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Price</th>
			<th>Discount %</th>
			<th>Discounted Price</th>
			<th>View Image</th>
			<th>Action</th>
		</tr>

		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.id}</td>

				<td>${product.name}</td>
				<td>₹${product.price}</td>
				<td>${product.discountOffer}%</td>
				<td>₹${product.discountprice}</td>
				<td><a
					href="${pageContext.request.contextPath}/product-images/${product.imgurl}"
					target="_blank">
						<button class="image-button">View Image</button>
				</a></td>
				<td>
					<form action="removeproduct" method="post">
						<input type="hidden" name="id" value="${product.id}" /> <input
							type="hidden" name="imgurl" value="${product.imgurl}" />
						<button class="remove-button">Remove</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<% String success = (String) request.getAttribute("success"); %>
	<% if (success != null) { %>
	<script>
       
            alert("<%= success %>");
           
    </script>
	<% } %>
	<% String error = (String) request.getAttribute("error"); %>
	<% if (error != null) { %>
	<script>
       
            alert("<%=error%>");
          
    </script>
	<% } %>
</body>
</html>