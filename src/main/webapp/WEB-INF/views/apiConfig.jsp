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
	<c:if test="${not empty sessionScope.msg }">
		<p>${sessionScope.msg}</p>
		<c:remove var="msg" scope="session"/>
	</c:if>
	<form action="apiConfig" method="post">
		URL: <input type="text" name="url" required/>
		API KEY: <input type="password" name="apiKey" required/>
		Choose Type: <select name="apiType">
			<option value="OKTA"> Okta </option>
			<option value="POSTMAN">Post Man</option>
		</select>
		<button type="submit">submit</button>
	</form>
</body>
</html>