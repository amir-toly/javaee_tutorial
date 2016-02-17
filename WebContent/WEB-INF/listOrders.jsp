<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Orders list</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css" />' />
	</head>
	
	<body>
		<c:import url="/inc/menu.jsp" />
		
		<div id="content">
			<c:choose>
				<c:when test="${ !empty sessionScope.orders }">
					<table>
						<tr>
							<th>Customer</th>
							<th>Date</th>
							<th>Amount</th>
							<th>Payment method</th>
							<th>Payment status</th>
							<th>Shipping mode</th>
							<th>Delivery status</th>
							<th class="action tableRightBorder">Action</th>
						</tr>
						
						<c:forEach items="${ sessionScope.orders }" var="order" varStatus="status">
							<c:set var="oddOrNothing" value="" />
							
							<c:if test="${ status.count % 2 != 0 }">
								<c:set var="oddOrNothing" value="odd" />
							</c:if>
							
							<tr class='<c:out value="${ oddOrNothing }"/>'>
								<td><c:out value="${ order.value.customer.firstName } ${ order.value.customer.lastName }"></c:out></td>
								<td><c:out value="${ order.value.date }"></c:out></td>
								<td><c:out value="${ order.value.amount }"></c:out></td>
								<td><c:out value="${ order.value.paymentMethod }"></c:out></td>
								<td><c:out value="${ order.value.paymentStatus }"></c:out></td>
								<td><c:out value="${ order.value.shippingMode }"></c:out></td>
								<td><c:out value="${ order.value.deliveryStatus }"></c:out></td>
								
								<td class="action tableRightBorder">
									<c:url value="/deleteOrder" var="deleteOrderLink">
										<c:param name="orderKey" value="${ order.key }" />
									</c:url>
									
									<a class="action" href="${ deleteOrderLink }">X</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p class="error">No orders created.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>
