<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Customer creation</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	<body>
		<%@ include file="inc/menu.jsp" %>
		<div>
			<form method="get" action="createCustomer">
				<c:import url="inc/inc_customer_form.jsp" />
				
				<input type="submit" value="Create" />
				<input type="reset" value="Reset" /> <br />
			</form>
		</div>
	</body>
</html>
