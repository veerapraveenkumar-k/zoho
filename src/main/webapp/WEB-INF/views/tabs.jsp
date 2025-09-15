<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	.tabs-container {
		width: 100%;
		height: 8vh;
		display: flex;
		align-items: center;
		margin-top: 20px;
		border-bottom: 1px solid gray;
	}
	
	.tab-btn {
		margin-right: 8px;
		cursor: pointer;
	}
</style>
</head>
<body>
	<div class="tabs-container">
		<c:if test="${user.getRole() eq 'SUPERADMIN' || user.getRole() eq 'ADMIN' || user.getRole() eq 'USER'}">
			<form action="profile">
				<button type="submit" class='tab-btn'>Profile</button>
			</form>
		</c:if>
		<c:if test="${user.getRole() eq 'SUPERADMIN'}">
			<form action="createAdmin">
				<button type="submit" class='tab-btn'>Create Admin</button>
			</form>
		</c:if>
		<c:if test="${user.getRole() eq 'ADMIN'}">
			<form action="importUsers">
				<button type="submit" class='tab-btn'>Import Users</button>
			</form>
		</c:if>
		<c:if test="${user.getRole() eq 'SUPERADMIN' || user.getRole() eq 'ADMIN'}">
			<form action="manageUsers">
				<button type="submit" class='tab-btn'>Manage Users</button>
			</form>
		</c:if>
	</div>
</body>
</html>