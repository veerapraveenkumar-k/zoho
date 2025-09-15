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
	box-sizing: border-box;
	font-family: sane serif;
}

.import-user-bg {
	width: 100%;
}

.import-user-header {
	width: 100%;
	height: 10vh;
	display: flex;
	align-items: center;
	padding: 20px;
}

.import-user-heading {
	font-size: 20px;
	color: black;
	font-weight: 300;
}
</style>
</head>
<body>
	<div class="import-user-bg">
		<header class="import-user-header">
			<h1 class="import-user-heading">Import Users</h1>
		</header>
		<c:choose>
			<c:when test="${not empty param.Import}">
				<jsp:include page="importConfig.jsp"/>
			</c:when>
			<c:otherwise>
				<jsp:include page="importOptions.jsp"/>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>