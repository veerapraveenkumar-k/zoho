<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:if test="${param.Import eq 'DB'}">
		<jsp:include page="dbConfig.jsp"/>
	</c:if>
	<c:if test="${param.Import eq 'JSON'}">
		<jsp:include page="jsonConfig.jsp"/>
	</c:if>
	<c:if test="${param.Import eq 'API'}">
		<jsp:include page="apiConfig.jsp"/>
	</c:if>
</body>
</html>