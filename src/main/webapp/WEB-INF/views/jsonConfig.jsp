<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/config.css">
</head>
<body>
	<h1>JSON CONFIG</h1>
	<form action="jsonConfig" method="post" enctype="multipart/form-data">
		<input type="file" name="jsonFile" accept=".json" required/>
		<button type="submit">upload</button>
	</form>
	<c:if test="${not empty sessionScope.msg }">
		<p>${sessionScope.msg}</p>
		<c:remove var="msg" scope="session"/>
	</c:if>
</body>
</html>