<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0);     // Proxies
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<style>
/* Reset Styles */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

/* Centering and Background */
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	padding: 20px;
}

/* Login Box */
.login-container {
	background: #ffffff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.3);
	text-align: center;
	width: 100%;
	max-width: 400px;
	transition: transform 0.3s ease-in-out;
}

.login-container:hover {
	transform: scale(1.03);
}

/* Heading */
h2 {
	margin-bottom: 20px;
	color: #333;
}

/* Input Fields */
input[type="email"], input[type="password"] {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: inset 3px 3px 8px rgba(0, 0, 0, 0.1);
	font-size: 16px;
}

/* Submit Button */
input[type="submit"] {
	width: 100%;
	padding: 12px;
	border: none;
	background: #28a745;
	color: white;
	font-size: 18px;
	cursor: pointer;
	border-radius: 8px;
	box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
	transition: 0.3s;
}

input[type="submit"]:hover {
	background: #218838;
}

/* Error Message */
.error-message {
	color: red;
	margin-top: 10px;
	font-size: 14px;
}

/* Responsive Design */
@media ( max-width : 480px) {
	.login-container {
		padding: 20px;
		box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
	}
	input[type="email"], input[type="password"] {
		padding: 10px;
	}
	input[type="submit"] {
		font-size: 16px;
	}
}
</style>
</head>
<body>

	<div class="login-container">
		<h2>Login</h2>
		<form action="doLogin" method="post">
			<input type="email" name="email" placeholder="Enter Email ID"
				required><br> <input type="password" name="password"
				placeholder="Enter Password" required><br> <input
				type="submit" value="Login">
		</form>
	</div>
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
	<script>
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            location.reload();
        }
    });
</script>
</body>
</html>

