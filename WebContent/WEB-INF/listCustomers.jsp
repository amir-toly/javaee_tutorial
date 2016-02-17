<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Customers list</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css" />' />
	</head>
	
	<body>
		<c:import url="/inc/menu.jsp" />
		
		<div id="content">
			<c:choose>
				<c:when test="${ !empty sessionScope.customers }">
					<table>
						<tr>
							<th>Last name</th>
							<th>First name</th>
							<th>Address</th>
							<th>Phone number</th>
							<th>Email</th>
							<th class="action tableRightBorder">Action</th>
						</tr>
						
						<c:forEach items="${ sessionScope.customers }" var="customer" varStatus="status">
							<c:set var="oddOrNothing" value="" />
							
							<c:if test="${ status.count % 2 != 0 }">
								<c:set var="oddOrNothing" value="odd" />
							</c:if>
							
							<tr class='<c:out value="${ oddOrNothing }"/>'>
								<td><c:out value="${ customer.value.lastName }"></c:out></td>
								<td><c:out value="${ customer.value.firstName }"></c:out></td>
								<td><c:out value="${ customer.value.address }"></c:out></td>
								<td><c:out value="${ customer.value.phoneNumber }"></c:out></td>
								<td><c:out value="${ customer.value.email }"></c:out></td>
								
								<td class="action tableRightBorder">
									<c:url value="/deleteCustomer" var="deleteCustomerLink">
										<c:param name="customerKey" value="${ customer.key }" />
									</c:url>
									
									<a class="action" href="${ deleteCustomerLink }">X</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p class="error">No customers created.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>
