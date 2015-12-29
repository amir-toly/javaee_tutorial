<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Order summary</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css" />' />
	</head>
	
	<body>
		<c:import url="/inc/menu.jsp" />
		
		<div id="content">
			<p class="info">
				${ requestScope.msg }
			</p>
			
			<c:if test="${ !requestScope.error }">
				<p>
					Customer
				</p>
				<p>
					Last name:
					<c:out value="${ requestScope.order.customer.lastName }" />
				</p>
				<p>
					First name:
					<c:out value="${ requestScope.order.customer.firstName }" />
				</p>
				<p>
					Address:
					<c:out value="${ requestScope.order.customer.address }" />
				</p>
				<p>
					Phone number:
					<c:out value="${ requestScope.order.customer.phoneNumber }" />
				</p>
				<p>
					Email:
					<c:out value="${ requestScope.order.customer.email }" />
				</p>
				<p>
					Order
				</p>
				<p>
					Date:
					<c:out value="${ requestScope.order.date }" />
				</p>
				<p>
					Amount:
					<c:out value="${ requestScope.order.amount }" />
				</p>
				<p>
					Payment method:
					<c:out value="${ requestScope.order.paymentMethod }" />
				</p>
				<p>
					Payment status:
					<c:out value="${ requestScope.order.paymentStatus }" />
				</p>
				<p>
					Shipping mode:
					<c:out value="${ requestScope.order.shippingMode }" />
				</p>
				<p>
					Delivery status:
					<c:out value="${ requestScope.order.deliveryStatus }" />
				</p>
			</c:if>
		</div>
	</body>
</html>
