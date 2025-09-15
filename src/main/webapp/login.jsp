<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="styleSheet" href="css/login.css">
</head>
<body>
<%
	if(request.getSession(false).getAttribute("user") != null){
		response.sendRedirect(request.getContextPath() + "/home");
		return;
	}
%>
	<form action="login" class="login-form" method="post">
		<div class="login-content-container">
			<h1 class="heading">zoho</h1>
			<div class="input-container">
				<label class="label">Enter User Name: </label>
				<input required type="text" name="username" class="input-box" placeholder="enter username..."/>
			</div>
			<div class="input-container">
				<label class="label">Enter Password: </label>
				<input required type="password" name="password" class="input-box" placeholder="enter password..."/>
			</div>
			<button type="submit" class="login-btn">Login</button>
			<%
				String err = (String) request.getAttribute("error");
				if(err != null){ %>
					<p class="error-msg"><%= err %></p>
				<%}
			%>
		</div>
	</form>
</body>
</html>