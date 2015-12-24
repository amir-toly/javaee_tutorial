<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Order summary</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	
	<body>
		<%@ include file="inc/menu.jsp" %>
		
		<div class="info">
			${ requestScope.msg }
		</div>
		
		<c:if test="${ requestScope.msg == 'Order created successfully!' }">
			<p>
				Customer
				<br />
				Last name:
				<c:out value="${ requestScope.order.customer.lastName }" />
				<br />
				First name:
				<c:out value="${ requestScope.order.customer.firstName }" />
				<br />
				Address:
				<c:out value="${ requestScope.order.customer.address }" />
				<br />
				Phone number:
				<c:out value="${ requestScope.order.customer.phoneNumber }" />
				<br />
				Email:
				<c:out value="${ requestScope.order.customer.email }" />
			</p>
			<p>
				Order
				<br />
				Date:
				<c:out value="${ requestScope.order.date }" />
				<br />
				Amount:
				<c:out value="${ requestScope.order.amount }" />
				<br />
				Payment method:
				<c:out value="${ requestScope.order.paymentMethod }" />
				<br />
				Payment status:
				<c:out value="${ requestScope.order.paymentStatus }" />
				<br />
				Shipping mode:
				<c:out value="${ requestScope.order.shippingMode }" />
				<br />
				Delivery status:
				<c:out value="${ requestScope.order.deliveryStatus }" />
			</p>
		</c:if>
	</body>
</html>
