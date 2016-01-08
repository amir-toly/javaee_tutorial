<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Customer creation</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css" />' />
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
		<div>
			<form method="post" action='<c:url value="/createCustomer" />'>
				<c:import url="/inc/inc_customer_form.jsp" />
				
				<p class="info">${ requestScope.form.result }</p>
				
				<input type="submit" value="Create" />
				<input type="reset" value="Reset" /> <br />
			</form>
		</div>
	</body>
</html>
