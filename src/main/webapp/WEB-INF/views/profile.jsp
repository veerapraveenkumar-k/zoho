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
		font-family: "sane serif";
		box-sizing: "border-box"
	}
	
	.profile-form {
		width: 100%;
		background-color: white;
	}
	
	.profile-header {
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
	
	.profile-btn-container {
		display: flex;
		align-items: center;
	}
	
	.save-edit-btn {
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
	
	.profile-details-container {
		width: 100%;
		display: flex;
		flex-direction: column;
		padding: 20px;
	}
	
	.element {
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
		box-shadow: 1px 1px 2px 0px gray;
	}
	
	.label {
		font-size: 18px;
		color: black;
		font-weight: 500;
		width: 200px;
		padding-left: 10px;
	}
	
	.colon {
		font-size: 18px;
		color: black;
		font-weight: 300;
		width: 80px;
	}
	
	.values {
		border: none;
		background-color: transparent;
		outline: none;
		font-size: 18px;
		color: black;
		font-weight: 100;
	}
	
	.id {
		border: none;
		background-color: transparent;
		outline: none;
		font-size: 18px;
		color: black;
		font-weight: 100;
	}
	
	.btn-disable {
		display: none;
	}
	
</style>
</head>
<body>
	<form action="updateProfile" method="post" class="profile-form">
		<nav class="profile-header">
			<h2 class="heading">My Profile</h2>
			<div class="profile-btn-container">
				<button type="button" class="save-edit-btn" id="EDIT">Edit</button>
				<button type="submit" class="save-edit-btn btn-disable" id="UPDATE">Update</button>
			</div>
		</nav>
		<p> ${msg} </p>
		<div class="profile-details-container">
			<div class="element">
				<h2 class="label">ID</h2>
				<p class="colon">:</p>
				<input name="ID" type="text" class="id" disabled value="${userProfile.getId()}"/>
			</div>
			<div class="element">
				<h2 class="label">User Name</h2>
				<p class="colon">:</p>
				<input name="USERNAME" type="text" class="id" disabled value="${userProfile.getUserName()}"/>
			</div>
			<div class="element">
				<h2 class="label">Password</h2>
				<p class="colon">:</p>
				<input name="PASSWORD" type="text" class="values" disabled value="${userProfile.getPassword()}"/>
			</div>
			<div class="element">
				<h2 class="label">Email</h2>
				<p class="colon">:</p>
				<input name="EMAIL" type="text" class="values" disabled value="${userProfile.getEmail()}"/>
			</div>
			<div class="element">
				<h2 class="label">Mobile No</h2>
				<p class="colon">:</p>
				<input name="MOBILE" type="text" class="values" disabled value="${userProfile.getMobileNum()}"/>
			</div>
		</div>
	</form>
<script>
const editBtn = document.getElementById("EDIT");
const updateBtn = document.getElementById("UPDATE")
const elements = document.querySelectorAll(".values");

editBtn.addEventListener("click", () => {
	editBtn.classList.add("btn-disable");
	updateBtn.classList.remove("btn-disable")
	elements.forEach(each => {
		each.disabled = false;
	})
})

</script>
</body>

</html>