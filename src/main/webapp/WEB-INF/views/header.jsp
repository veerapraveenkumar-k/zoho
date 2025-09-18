<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	*{
		box-sizing: border-box;
		font-family: sane serif;
	}
	
	.header{
		width: 100%;
		height: 10vh;
		display: flex;
		align-items: center;
		justify-content: space-between;
		background-color: white;
		border-bottom: 1px solid gray;
		padding: 0px 20px 0px 20px;
	}
	
	.welcome {
		font-size: 20px;
	}
	
	.logout-btn {
		cursor: pointer;
	}
</style>
</head>
<body>
	<form class="header" action="logout" method="post">
		<h2 class="welcome">Welcome ${user.getUserName()} - ${user.getRole()}</h2>
		<button class="logout-btn" type="submit">Logout</button>
	</form>
</body>
</html>