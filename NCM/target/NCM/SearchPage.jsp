<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
/* General Styles */
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	padding: 20px;
	margin: 0;
	font-family: Arial, sans-serif;
	text-align: center;
}

/* 3D Heading Effect */
h2 {
	color: white;
	font-size: 2.2rem;
	font-weight: bold;
	text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
	margin-bottom: 20px;
}

/* Search Container */
.search-container {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 10px;
	flex-wrap: wrap;
	max-width: 500px;
	width: 100%;
	margin: auto;
}

/* Search Bar */
.search-bar {
	flex: 1;
	padding: 12px;
	border: 2px solid #007bff;
	border-radius: 5px;
	font-size: 1rem;
	box-shadow: inset 2px 2px 4px rgba(0, 0, 0, 0.2);
	min-width: 200px;
}

/* 3D Search Button */
.search-button {
	background: linear-gradient(to bottom, #007bff, #0056b3);
	color: white;
	border: none;
	padding: 12px 18px;
	border-radius: 8px;
	cursor: pointer;
	font-size: 1rem;
	font-weight: bold;
	box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
	transition: all 0.2s ease-in-out;
}

.search-button:hover {
	background: linear-gradient(to bottom, #0056b3, #004099);
	transform: translateY(2px);
	box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
}

/* Error Message */
.error-message {
	color: red;
	font-weight: bold;
	display: none;
	margin-top: 20px;
}

/* Shopkeeper Button Container */
.shopkeeper-buttons {
	display: flex;
	justify-content: center;
	gap: 20px;
	margin-top: 30px;
	flex-wrap: wrap;
}

.shopkeeper-button {
	background: linear-gradient(to bottom, #28a745, #1e7e34);
	color: white;
	border: none;
	padding: 12px 20px;
	border-radius: 8px;
	cursor: pointer;
	font-size: 1rem;
	font-weight: bold;
	box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
	transition: all 0.2s ease-in-out;
}

.shopkeeper-button:hover {
	background: linear-gradient(to bottom, #1e7e34, #155d27);
	transform: translateY(2px);
	box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
}

/* Mobile Styles */
@media ( max-width : 768px) {
	h2 {
		font-size: 2rem;
	}
	.search-bar {
		width: 90%;
		font-size: 1rem;
	}
	.search-button, .shopkeeper-button {
		width: 100%;
		padding: 10px;
		font-size: 1rem;
	}
}

/* Large Screens */
@media ( min-width : 1200px) {
	h2 {
		font-size: 3rem;
	}
	.search-bar {
		width: 400px;
	}
	.search-button {
		padding: 14px 20px;
		font-size: 1.1rem;
	}
	.shopkeeper-button {
		padding: 14px 22px;
		font-size: 1.1rem;
	}
}
</style>
<script>
// This function runs when the page loads
function checkLocationAccess() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function(position) {
                // Show the main content
                document.getElementById("content").style.display = "block";
                document.getElementById("error").style.display = "none";

                // Set the hidden input values
                document.getElementById("latitude").value = position.coords.latitude;
                document.getElementById("longitude").value = position.coords.longitude;
            },
            function(error) {
                // Hide content, show error
                document.getElementById("content").style.display = "none";
                document.getElementById("error").style.display = "block";
                document.getElementById("error").innerText = "Location access is required to use this page. Please enable location services.";
            },
            {
            enableHighAccuracy: true,
            timeout: 10000,
            maximumAge: 0
            }
        );
    } else {
        document.getElementById("error").innerText = "Geolocation is not supported by your browser.";
        document.getElementById("error").style.display = "block";
    }
}
</script>


</head>
<body onload="checkLocationAccess()">

	<div id="error" class="error-message">Location access is required
		to view this page. Please enable location services.</div>

	<div id="content" style="display: none;">
		<h2>National City Market</h2>
		<div class="search-container">
			<form action="search" method="get">
				<input type="text" class="search-bar" name="search"
					placeholder="Search for Products, Brands, and More">
				<!-- Hidden fields to send latitude and longitude -->
				<input type="hidden" id="latitude" name="latitude" value="">
				<input type="hidden" id="longitude" name="longitude" value="">
				<button class="search-button">🔍</button>
			</form>
		</div>

		<!-- New Buttons -->
		<div class="shopkeeper-buttons">
			<form action="login" method="get">
				<button class="shopkeeper-button" type="submit">👤 Login as
					Shopkeeper</button>
			</form>
			<form action="createaccount" method="get">
				<button class="shopkeeper-button" type="submit">📝 Register
					as Shopkeeper</button>
			</form>
		</div>
	</div>

</body>
</html>

