<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
	font-family: sane serif;
	box-sizing: border-box;
}

.config-db-form {
	width: 100%;
	background-color: white;
	padding: 20px;
}
.config-db-header {
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 20px;
	padding-right: 100px;
}

.success-error-heading {
	font-size: 20px;
	color: black;
	font-weight: 400;
	margin: 20px;
}

.create-instance-link {
	text-decoration: none;
	height: 28px;
	width: 100px;
	border: 1px solid gray;
	padding: 10px;
	margin: 20px;
}

.config-heading {
	font-size: 20px;
	color: black;
	font-weight: 400;
}

.config-btn {
	height: 28px;
	border: 1px solid black;
	border-radius: 2px;
	background-color: lightblue;
	color: black;
	font-size: 15px;
	font-weight: 500;
	outline: none;
	cursor: pointer;
}

.config-content-container {
	width: 100%;
	margin-top: 10px;
	display: flex;
	flex-direction: column;
	margin-bottom: 20px;
	height: 50vh;
	overflow: auto;
}

.config-element {
	width: 100%;
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

.lebel-text {
	font-size: 18px;
	color: black;
	width: 200px;
	font-weight: 500;
}

.colon {
	font-size: 18px;
	color: black;
	width: 80px;
}

.input-box {
	width: 200px;
	height: 28px;
	border: 1px solid gray;
	border-radius: 2px;
	background-color: transparent;
	outline: none;
	color: black;
	font-size: 16px;
	padding: 0px 8px 0px 8px;
}
	
</style>
</head>
<body>
	<form class="config-db-form" action="dbConfig" method="post">
	<c:if test="${not empty sessionScope.msg }">
		<p>${sessionScope.msg}</p>
		<c:remove var="msg" scope="session"/>
	</c:if>
		<header class="config-db-header">
			<h1 class="config-heading">Database Configuration: </h1>
			<button type="submit" class="config-btn" name="action" value="DB">Create Instance</button>
		</header>
		<div class="config-content-container">
			<div class="config-element">
				<h1 class="lebel-text">Choose Host</h1>
				<p class="colon">:</p>
				<select id="host" name="HOST" class="input-box">
					<option value="localhost">Local Host</option>
				</select>
			</div>
			<div class="config-element">
				<h1 class="lebel-text">Enter Port No</h1>
				<p class="colon">:</p>
				<input type="text" class="input-box" placeholder="enter port num.." name="PORT"/>
			</div>
			<div class="config-element">
				<h1 class="lebel-text">Enter DB Name</h1>
				<p class="colon">:</p>
				<input type="text" class="input-box" placeholder="enter db name.." name="DBNAME"/>
			</div>
			<div class="config-element">
				<h1 class="lebel-text">Enter Table Name</h1>
				<p class="colon">:</p>
				<input type="text" class="input-box" placeholder="enter table name.." name="TABLE"/>
			</div>
			<div class="config-element">
				<h1 class="lebel-text">Enter User Name</h1>
				<p class="colon">:</p>
				<input type="text" class="input-box" placeholder="enter username.." name="UNAME"/>
			</div>
			<div class="config-element">
				<h1 class="lebel-text">Enter Password</h1>
				<p class="colon">:</p>
				<input type="password" class="input-box" placeholder="enter password.." name="PASS"/>
			</div>
		</div>
	</form>
</body>
</html>