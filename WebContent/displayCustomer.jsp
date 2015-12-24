<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Customer summary</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	
	<body>
		<%@ include file="inc/menu.jsp" %>
		
		<div class="info">
			${ requestScope.msg }
		</div>
		
		<c:if test="${ requestScope.msg == 'Customer created successfully!' }">
			<p>
				Last name:
				<c:out value="${ requestScope.customer.lastName }" />
				<br />
				First name:
				<c:out value="${ requestScope.customer.firstName }" />
				<br />
				Address:
				<c:out value="${ requestScope.customer.address }" />
				<br />
				Phone number:
				<c:out value="${ requestScope.customer.phoneNumber }" />
				<br />
				Email:
				<c:out value="${ requestScope.customer.email }" />
			</p>
		</c:if>
	</body>
</html>
