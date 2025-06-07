<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0);     // Proxies
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shops</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	padding: 20px;
}

.container {
	width: 90vw; /* 90% width of viewport */
	height: 100vh;
	overflow-y: scroll;
	scrollbar-width: none; /* Hide scrollbar for Firefox */
	-ms-overflow-style: none; /* Hide scrollbar for IE/Edge */
	padding: 20px 0;
}

.container::-webkit-scrollbar {
	display: none; /* Hide scrollbar for Chrome, Safari */
}

.box {
	width: 100%;
	height: 80px;
	margin: 10px 0;
	background: linear-gradient(145deg, #007bff, #0056b3);
	box-shadow: 3px 3px 8px rgba(0, 0, 0, 0.2), -3px -3px 8px
		rgba(255, 255, 255, 0.1);
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	border-radius: 10px;
	transition: all 0.2s ease-in-out;
}

.box:active {
	transform: scale(0.98);
	box-shadow: inset 3px 3px 8px rgba(0, 0, 0, 0.3), inset -3px -3px 8px
		rgba(255, 255, 255, 0.1);
}

.btn {
	width: 100%;
	height: 100%;
	background: transparent;
	border: none;
	color: white;
	font-size: 18px;
	font-weight: bold;
	cursor: pointer;
	outline: none;
	border-radius: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<c:forEach var="entry" items="${sortedByDistances}">
			<div class="box">
				<button class="btn"
					onclick="window.location.href='shopDetails?shopKey=${entry.key}&shopName=${shopnames[entry.key]}'">
					${shopnames[entry.key]}</button>
			</div>
		</c:forEach>
	</div>

<script>
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            location.reload(); // Force refresh from server
        }
    });
</script>

</body>