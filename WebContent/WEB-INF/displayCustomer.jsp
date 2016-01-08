<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Customer summary</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css" />' />
	</head>
	
	<body>
		<c:import url="/inc/menu.jsp" />
		
		<div id="content">
			<p class="info">
				${ requestScope.form.result }
			</p>
			
			<p>
				Last name:
				<c:out value="${ requestScope.customer.lastName }" />
			</p>
			<p>
				First name:
				<c:out value="${ requestScope.customer.firstName }" />
			</p>
			<p>
				Address:
				<c:out value="${ requestScope.customer.address }" />
			</p>
			<p>
				Phone number:
				<c:out value="${ requestScope.customer.phoneNumber }" />
			</p>
			<p>
				Email:
				<c:out value="${ requestScope.customer.email }" />
			</p>
		</div>
	</body>
</html>
