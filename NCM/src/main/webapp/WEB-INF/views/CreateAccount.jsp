<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0);     // Proxies
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

html, body {
	height: 100%;
	width: 100%;
	font-family: Arial, sans-serif;
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	overflow-y: scroll;
	scrollbar-width: none; /* Firefox */
}

/* Hide scrollbar for Chrome, Safari, Edge */
body::-webkit-scrollbar {
	display: none;
}

.container {
	max-width: 400px;
	width: 90%;
	margin: 40px auto;
	padding: 20px;
	background: #fff;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
}

label {
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"], input[type="email"], input[type="password"], input[type="number"]
	{
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

button {
	width: 100%;
	padding: 10px;
	background: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	margin-top: 10px;
}

button:hover {
	background: #0056b3;
}

.send-otp-btn {
	background: #28a745;
	margin-bottom: 15px;
}

@media screen and (max-width: 480px) {
	.container {
		margin: 20px auto;
	}
}
</style>
</head>
<body>

	<div class="container">
		<h2>Create Account</h2>
		<form action="register" method="post">
			<label for="shopName">Shop Name:</label> <input type="text"
				id="shopName" name="shopName" required> <label for="email">Email:</label>
			<input type="email" id="email" name="email" required>

			<button type="button" class="send-otp-btn" onclick="sendOtp()">Send
				OTP to Email</button>

			<label for="otp">OTP:</label> <input type="number" id="otp"
				name="otp" required> <label for="password">Password:</label>
			<input type="password" id="password" name="password" required>

			<label for="confirmPassword">Confirm Password:</label> <input
				type="password" id="confirmPassword" name="confirmPassword" required>

			<button type="submit">Create Account</button>
		</form>
	</div>

	<script>
    function sendOtp() {
        const email = document.querySelector('#email').value;
        if (!email) {
            alert("Please enter your email first.");
            return;
        }
fetch("sendOtp?email=" + encodeURIComponent(email))
    .then(response => response.text())  // plain text, not JSON
    .then(message => {
        alert(message);
    });
    }
</script>
	<c:if test="${not empty error}">
		<script>
        alert("${error}");
    </script>
	</c:if>

	<script>
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            location.reload(); // Force refresh from server
        }
    });
</script>
</body>
</html>
