<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
		.user-table {
		width: 90%;
		border: 1px solid gray;
		border-collapse: collapse;
		margin: 30px;
	}
	
	.table-header {
		background-color: #c0c0c0;
		border: 1px solid gray;
		height: 30px;
		text-align: left;
	}
	
	.head-element {
		padding-left: 10px;
		border-right: 1px solid gray;
	}
	
	.body {
		height: 40px;
		border: 1px solid gray;
		padding-left: 10px;
	}
	
</style>
</head>
<body>
	<c:if test="${not empty sessionScope.msg}">
		<p> ${sessionScope.msg} </p>
		<c:remove var="msg" scope="session"/>
	</c:if>
	<table  class="user-table">
		<thead class="table-header">
			<tr>
				<c:if test="${role eq 'SUPERADMIN' || role eq 'ADMIN'}"><th class="head-element">ID</th></c:if>
				<c:if test="${role eq 'SUPERADMIN' || role eq 'ADMIN'}"><th class="head-element">User Name</th></c:if>
				<c:if test="${role eq 'ADMIN'}"><th class="head-element">Instance Id</th></c:if>
				<c:if test="${role eq 'ADMIN'}"><th class="head-element">Admin Id</th></c:if>
				<c:if test="${role eq 'ADMIN'}"><th class="head-element">Type</th></c:if>
				<c:if test="${role eq 'ADMIN'}"><th class="head-element">Action</th></c:if>
				<c:if test="${role eq 'SUPERADMIN'}"><th class="head-element">Manage</th></c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="users" items="${usersList}">
				<tr>
					<c:if test="${role eq 'SUPERADMIN' || role eq 'ADMIN'}"><td class="body"> ${users.getId()} </td></c:if>
					<c:if test="${role eq 'SUPERADMIN' || role eq 'ADMIN'}"><td class="body">${users.getUserName()}</td></c:if>
					<c:if test="${role eq 'ADMIN'}"><td class="body">${users.getInstanceId()}</td></c:if>
					<c:if test="${role eq 'ADMIN'}"><td class="body">${users.getAdminId()}</td></c:if>
					<c:if test="${role eq 'ADMIN'}"><td class="body">${users.getSource()}</td></c:if>
					<c:if test="${role eq 'ADMIN'}"><td class="body">
					<c:if test="${user.getId() eq users.getAdminId()}">
					<form action="deleteUsers" method="post">
						<button value="${users.getId()}" class="import-btn" type="submit" name="delete"> Delete </button>
					</form>
					</c:if>
					</td>
					</c:if>
					<c:if test="${role eq 'SUPERADMIN'}"><td class="body">
					<form action="manageAdmin" method="post">
						<button value="${users.getId()}" class="import-btn" type="submit" name="manage"> Manage </button>
					</form>
					</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>