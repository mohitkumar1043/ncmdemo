<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0);     // Proxies
%>
<%
    if (session.getAttribute("admin") == null) {
    	 response.sendRedirect("Login.jsp");
         return;
        
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>home</title>
<style>
.button-form {
	width: 100%;
}

.button-form button {
	width: 100%;
}

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	height: 100vh;
	width: 100%;
	background: linear-gradient(135deg, #1e3c72, #2a5298);
	font-family: 'Poppins', Arial, sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.title {
	font-size: 42px;
	color: white;
	font-weight: 800;
	text-shadow: 2px 4px 8px rgba(0, 0, 0, 0.7); /* improved 3D effect */
	margin-bottom: 50px;
	text-align: center;
}

.button-container {
	display: flex;
	flex-direction: column;
	gap: 25px;
	width: 90%;
	max-width: 400px;
}

.custom-button {
	padding: 18px;
	background: linear-gradient(145deg, #34d058, #28a745);
	color: white;
	border: none;
	border-radius: 12px;
	font-size: 20px;
	font-weight: 600;
	cursor: pointer;
	box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.6), -3px -3px 10px
		rgba(255, 255, 255, 0.1); /* soft glassy 3D */
	transition: all 0.3s ease-in-out;
}

.custom-button:hover {
	transform: translateY(-5px) scale(1.02);
	box-shadow: 8px 8px 20px rgba(0, 0, 0, 0.7), -4px -4px 12px
		rgba(255, 255, 255, 0.15);
	background: linear-gradient(145deg, #28a745, #23913d);
}

@media ( max-width : 600px) {
	.title {
		font-size: 30px;
		margin-bottom: 30px;
	}
	.custom-button {
		font-size: 18px;
		padding: 15px;
	}
}
</style>

<script>
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function(position) {
                var latitude = position.coords.latitude;
                var longitude = position.coords.longitude;

                console.log("Latitude: " + latitude);
                console.log("Longitude: " + longitude);

                // Build URL-encoded parameters properly
                const params = new URLSearchParams();
                params.append('latitude', latitude);
                params.append('longitude', longitude);

                // Send latitude and longitude to server
                fetch('storelocation', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: params
                })
                .then(response => response.text())
                .then(data => {
                    if (data === "success") {
                        alert("Location stored successfully!");
                        document.getElementById("locationButton").innerText = "✏️ Edit Shop Location";
                    } else {
                        alert("Failed to store location.");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                });

            },
            function(error) {
                alert("Geolocation Error: " + error.message);
            },
            {
                enableHighAccuracy: true,
            timeout: 10000,
            maximumAge: 0
            }
        );
    } else {
        alert("Geolocation is not supported by your browser.");
    }
}
</script>

</head>
<body>

	<div class="title">Welcome to NCM Admin Page</div>

	<div class="button-container">
		<form action="getaddnewproductpage" method="get" class="button-form">
			<button class="custom-button" type="submit">➕ Add New
				Product</button>
		</form>
		<form action="getproductlists" method="get" class="button-form">
			<button class="custom-button">🛠️Products Lists</button>
		</form>
		<button id="locationButton" class="custom-button"
			onclick="getLocation()">📍 Give Shop Location</button>
		<form action="logout" method="get" class="button-form">
			<button class="custom-button">🚪 LogOut</button>
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
            location.reload(); // Force refresh from server
        }
    });
</script>
</body>
</html>
