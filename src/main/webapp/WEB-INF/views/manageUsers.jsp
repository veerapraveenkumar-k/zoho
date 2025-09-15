<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<table  class="user-table">
				<thead class="table-header">
					<tr>
						<th class="head-element">ID</th>
						<th class="head-element">User Name</th>
						<th class="head-element">Instance Id</th>
						<th class="head-element">Admin Id</th>
						<th class="head-element">Type</th>
						<th class="head-element">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="users" items="${usersList}">
						<tr>
							<td class="body"> ${users.getId()} </td>
							<td class="body">${users.getUserName()}</td>
							<td class="body">${users.getInstanceId()}</td>
							<td class="body">${users.getAdminId()}</td>
							<td class="body">${users.getSource()}</td>
							<td class="body">
							<c:if test="${user.getId() eq users.getAdminId()}">
								<form>
									<button class="import-btn" type="submit" name="delete"> Delete </button>
								</form>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</body>
</html>