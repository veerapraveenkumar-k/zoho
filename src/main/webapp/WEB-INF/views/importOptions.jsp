<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		.import-page-bg {
		width: 100%;
		padding: 20px;
	}
	
	.access-list-container {
		width: 100%;
		display: flex;
		align-items: center;
	}
	
	.access-container {
		width: 200px;
		height: 70px;
		border: 1px solid gray;
		border-radius: 10px;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-right: 20px;
	}
	
	.user-table {
		width: 90%;
		border: 1px solid gray;
		border-collapse: collapse;
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
	
	a{
		text-decoration: none;
		color: black;
		font-size: 16px;
	}

</style>
</head>
<body>
	<div class="import-page-bg">
		<div class="access-list-container">
			<c:if test="${adminObject.getDbAccess()}">
				<div class="access-container">
					<a href="importUsers?Import=DB">Import FROM DB</a>
				</div>
			</c:if>
			<c:if test="${adminObject.getJsonAccess()}">
				<div class="access-container">
					<a href="importUsers?Import=JSON">Import FROM JSON</a>
				</div>
			</c:if>
			<c:if test="${adminObject.getIamAccess()}">
				<div class="access-container">
					<a href="importUsers?Import=API">Import FROM API'S</a>
				</div>
			</c:if>
		</div>
		<c:if test="${instanceList.size() > 0}">
			<h1>Instance List: </h1>
			<table  class="user-table">
				<thead class="table-header">
					<tr>
						<th class="head-element">Instance ID</th>
						<th class="head-element">Type</th>
						<th class="head-element">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="instance" items="${instanceList}">
						<tr>
							<td class="body">${instance.getId()}</td>
							<td class="body">${instance.getType()}</td>
							<td class="body">
								<form action="importUsers" method="post">
									<button class="import-btn" value="${instance.getId()}" type="submit" name="import"> Import </button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<p>${msg}</p>
		</c:if>
	</div>
</body>
</html>