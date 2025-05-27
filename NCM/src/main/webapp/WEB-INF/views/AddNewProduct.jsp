<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Add Product</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
	padding: 20px;
	overflow: hidden;
	-webkit-tap-highlight-color: transparent;
}

.container {
	max-width: 500px;
	width: 90%;
	height: 85vh;
	padding: 25px;
	background: white;
	border-radius: 15px;
	position: relative;
	transform: rotateY(10deg) rotateX(5deg);
	box-shadow: 12px 12px 35px rgba(0, 0, 0, 0.3);
	transition: transform 0.3s ease-in-out;
	overflow-y: auto;
	scrollbar-width: none;
}

.container::-webkit-scrollbar {
	display: none;
}

h2 {
	color: #333;
	font-weight: bold;
	text-align: center;
	text-shadow: 2px 2px 5px rgba(0, 0, 255, 0.3);
}

form {
	padding-bottom: 20px;
}

label {
	font-weight: bold;
}

.form-control {
	border-radius: 8px;
	box-shadow: 3px 3px 8px rgba(0, 0, 0, 0.1);
	transform: perspective(800px) rotateX(3deg);
	transition: box-shadow 0.2s ease-in-out;
}

.form-control:focus {
	box-shadow: 0 0 5px #0d6efd;
}

.btn-custom {
	width: 100%;
	background-color: #28a745;
	color: white;
	font-size: 16px;
	padding: 12px;
	border: none;
	border-radius: 5px;
	box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
	transition: transform 0.3s, background-color 0.3s;
}

.btn-custom:hover {
	background-color: #218838;
	transform: scale(1.05);
}

@media screen and (max-width: 768px) {
	.container {
		height: 90vh;
		max-width: 95%;
		transform: none;
	}
	.form-control {
		transform: none;
	}
}
</style>
</head>
<body>

	<div class="container">
		<h2>Add New Product</h2>
		<form action="AddProduct" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="name" class="form-label">Product Name</label> <input
					type="text" class="form-control" id="name" name="name" required>
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Product Price (₹)</label> <input
					type="number" class="form-control" id="price" name="price" required>
			</div>

			<div class="mb-3">
				<label for="offer" class="form-label">Discount Offer (%)</label> <input
					type="number" class="form-control" id="offer" name="offer" min="0"
					max="100" required>
			</div>

			<div class="mb-3">
				<label for="image" class="form-label">Upload Product Image</label> <input
					type="file" class="form-control" id="image" name="image"
					accept="image/*" required>
			</div>

			<button type="submit" class="btn btn-custom" aria-label="Add Product">Add
				Product</button>
		</form>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<% String success = (String) request.getAttribute("success"); %>
	<% if (success != null) { %>
	<script>
        alert("<%= success %>");
    </script>
	<% } %>
	<% String error = (String) request.getAttribute("error"); %>
	<% if (error != null) { %>
	<script>
        alert("<%= error %>");
    </script>
	<% } %>
	<script>
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            location.reload(); // Force refresh from server
        }
    });
</script>
</body>
</html>


