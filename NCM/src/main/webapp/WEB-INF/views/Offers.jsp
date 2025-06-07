<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0);     // Proxies
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shop</title>
<style>
body {
	display: flex;
	flex-direction: column;
	align-items: center;
	height: 100vh;
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	margin: 0;
	padding: 0;
}

/* 3D Shop Name */
.shop-name {
	text-align: center;
	font-size: 28px;
	color: white;
	font-weight: bold;
	text-shadow: 5px 5px 10px rgba(0, 0, 255, 0.5);
	margin: 10px 0;
}

/* Full-Screen Scrollable Product Section */
.product-container {
	width: 100%;
	max-width: 1200px; /* Increased for better layout */
	height: 100vh; /* Full screen height */
	overflow-y: auto; /* Enable scrolling */
	padding: 10px;
	background: rgba(255, 255, 255, 0.1);
	border-radius: 10px;
}

/* Custom Scrollbar */
.product-container::-webkit-scrollbar {
	width: 10px;
}

.product-container::-webkit-scrollbar-thumb {
	background: white;
	border-radius: 10px;
}

.product-container::-webkit-scrollbar-track {
	background: rgba(255, 255, 255, 0.2);
}

/* Product Grid */
.product-grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	/* 4 products per row on desktop */
	gap: 20px;
	padding-bottom: 10px;
}

/* Product Card */
.product {
	padding: 15px;
	border: 1px solid #ccc;
	text-align: center;
	background: white;
	border-radius: 10px;
	box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.2);
	transition: transform 0.3s;
}

.product:hover {
	transform: scale(1.05);
}

.product img {
	width: 100%;
	height: auto;
	border-radius: 5px;
}

.price {
	font-size: 18px;
	font-weight: bold;
	color: red;
}

.old-price {
	text-decoration: line-through;
	color: gray;
}

/* 3D Shop Location Button */
.shop-location {
	margin: 10px 0;
}

.shop-location button {
	background-color: green;
	color: white;
	font-size: 16px;
	padding: 10px 20px;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
	transition: transform 0.3s, background-color 0.3s;
}

.shop-location button:hover {
	background-color: darkgreen;
	transform: scale(1.1);
}

/* Responsive Design */
@media ( max-width : 1024px) {
	.product-grid {
		grid-template-columns: repeat(2, 1fr);
		/* 2 products per row on tablets */
	}
}

@media ( max-width : 600px) {
	.product-grid {
		grid-template-columns: repeat(1, 1fr);
		/* 1 product per row on mobile */
	}
}
</style>
</head>
<body>

	<!-- Shop Name -->
	<h2 class="shop-name">${shopname}</h2>
	<!-- Product Section -->
	<div class="product-container">
		<div class="product-grid">
			<c:forEach var="product" items="${products}">
				<div class="product">
					<img
						src="${product.imgurl}"alt="${product.name}" width="150" height="150" />
					<p>${product.name}</p>
					<p class="old-price">₹${product.price}</p>
					<p class="price">₹${product.discountprice}
						(${product.discountOffer}% OFF)</p>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="shop-location">
		<button
			onclick="window.open('https://www.google.com/maps/dir/?api=1&origin=${ulatitude},${ulongitude}&destination=${slatitude},${slongitude}&travelmode=driving','_self')">
			Get Shop Location</button>
	</div>
<script>
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            location.reload(); // Force refresh from server
        }
    });
</script>
</body>
</html>


