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

.create-admin-form {
	width: 100%;
}

.admin-header {
	width: 100%;
	height: 10vh;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 20px;
}

.heading {
	font-size: 20px;
	color: black;
	font-weight: 300;
}

.create-admin-btn {
	width: 80px;
	height: 26px;
	background-color: lightblue;
	border: none;
	outline: none;
	cursor: pointer;
	margin-right: 20px;
	color: black;
	font-size: 13px;
	font-weight: bold;
	border-radius: 5px;
}

.create-admin-content-container {
	width: 100%;
	padding: 20px;
	display: flex;
	flex-direction: column;
}

.input-feilds-container {
	width: 100%;
	display: flex;
	align-items: center;
}

.input-label {
	font-size: 16px;
	color: black;
	font-weight: 400;
	width: 200px;
}

.colon {
	font-size: 16px;
	color: black;
	font-weight: 400;
	width: 80px;
}

.input-box {
	width: 250px;
	height: 28px;
	border: 1px solid gray;
	background-color: transparent;
	border-radius: 2px;
	outline: none;
	cursor: text;
	font-size: 14px;
	color: black;
	padding: 0px 8px 0px 8px;
}

.access-input-feilds-container {
	width: 100%;
	display: flex;
	align-items: flex-start;
}

.access-feild-container {
	width: 300px;
	display: flex;
	flex-direction: column;
}

.checkbox-container {
	display: flex;
	align-items: center;
	margin-top: 20px;
}

.check-box {
	width: 17px;
	height: 17px;
	border: 1px solid gray;
	outline: none;
	cursor: pointer;
	margin-right: 18px;
}

.check-box-label {
	font-size: 16px;
	color: black;
	font-weight: 400;
	cursor: pointer;
}
</style>
</head>
<body>
	<form class="create-admin-form" action="createAdmin" method="post">
	<p> ${msg} </p>
		<header class="admin-header">
			<h1 class="heading">Create Admin</h1>
			<button class="create-admin-btn" type="submit">Create</button>
		</header>
		<div class="create-admin-content-container">
			<div class="input-feilds-container">
				<h2 class="input-label">User Name</h2>
				<p class="colon">:</p>
				<input name="username" type="text" class="input-box" placeholder="enter username..."/>
			</div>
			<div class="input-feilds-container">
				<h2 class="input-label">Password</h2>
				<p class="colon">:</p>
				<input name="password" type="password" class="input-box" placeholder="enter password..."/>
			</div>
			<div class="access-input-feilds-container">
				<h2 class="input-label">Select Access</h2>
				<p class="colon">:</p>
				<div class="access-feild-container">
					<div class="checkbox-container">
						<input name="DATABASEACCESS" type="checkbox" class="check-box" id="DATABASEACCESS"/>
						<label for="DATABASEACCESS" class="check-box-label">Database Access</label>
					</div>
					<div class="checkbox-container">
						<input name="JSONACCESS" type="checkbox" class="check-box" id="JSONACCES"/>
						<label for="JSONACCESS" class="check-box-label">JSON File Access</label>
					</div>
					<div class="checkbox-container">
						<input name="IAMUSERACCESS" type="checkbox" class="check-box" id="IAMUSERACCESS"/>
						<label for="IAMUSERACCESS" class="check-box-label">IAM User Access</label>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>