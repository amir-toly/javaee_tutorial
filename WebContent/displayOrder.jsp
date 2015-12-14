<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Order summary</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	
	<body>
		<div class="info">
			${ requestScope.msg }
		</div>
		<p>
			Customer
			<br />
			Last name:
			${ requestScope.order.customer.lastName }
			<br />
			First name:
			${ requestScope.order.customer.firstName }
			<br />
			Address:
			${ requestScope.order.customer.address }
			<br />
			Phone number:
			${ requestScope.order.customer.phoneNumber }
			<br />
			Email:
			${ requestScope.order.customer.email }
		</p>
		<p>
			Order
			<br />
			Date:
			${ requestScope.order.date }
			<br />
			Amount:
			${ requestScope.order.amount }
			<br />
			Payment method:
			${ requestScope.order.paymentMethod }
			<br />
			Payment status:
			${ requestScope.order.paymentStatus }
			<br />
			Shipping mode:
			${ requestScope.order.shippingMode }
			<br />
			Delivery status:
			${ requestScope.order.deliveryStatus }
		</p>
	</body>
</html>
