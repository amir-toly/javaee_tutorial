<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Customer summary</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	
	<body>
		<div class="info">
			${ requestScope.msg }
		</div>
		<p>
			Last name:
			${ requestScope.customer.lastName }
			<br />
			First name:
			${ requestScope.customer.firstName }
			<br />
			Address:
			${ requestScope.customer.address }
			<br />
			Phone number:
			${ requestScope.customer.phoneNumber }
			<br />
			Email:
			${ requestScope.customer.email }
		</p>
	</body>
</html>
