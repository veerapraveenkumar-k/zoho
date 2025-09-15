<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	*{
		box-sizing: border-box;
		font-family: sane serif;
	}
	
	.home-bg {
		width: 100%;
		height: 100vh;
		background-color: white;
	}
</style>
</head>
<body>
<%

%>
	<div class="home-bg">
		<jsp:include page="header.jsp"/>
		<jsp:include page="tabs.jsp"/>
		<div class="content-container">
			<c:choose>
				<c:when test="${activeTab eq 'profile'}">
					<jsp:include page="profile.jsp"/>
				</c:when>
				<c:when test="${activeTab eq 'createAdmin'}">
					<jsp:include page="createAdmin.jsp"/>
				</c:when>
				<c:when test="${activeTab eq 'importUsers'}">
					<jsp:include page="importUsers.jsp"/>
				</c:when>
				<c:when test="${activeTab eq 'manageUsers'}">
					<jsp:include page="manageUsers.jsp"/>
				</c:when>
			</c:choose>
		</div>
	</div>
</body>
</html>