<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Order creation</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css" />' />
	</head>
	
	<body>
		<c:import url="/inc/menu.jsp" />
		
		<div>
			<form method="post" action='<c:url value="/createOrder" />'>
				<c:set var="customer" value="${ requestScope.order.customer }" scope="request" />
				
				<fieldset>
					<legend>Customer details</legend>
					
					<div class="labelFormForRadio">New customer? <span class="required">*</span></div>
					<input
						type="radio" name="newCustomer" value="yes" id="yes" onclick="toggle('newCustomer', 'existingCustomer')"
						<c:if test='${ requestScope.form.yesChecked }'>checked</c:if>
					/>
					<label class="radioLabel" for="yes">Yes</label>
					<input
						type="radio" name="newCustomer" value="no" id="no" onclick="toggle('existingCustomer', 'newCustomer')"
						<c:if test='${ requestScope.form.noChecked }'>checked</c:if>
					/>
					<label class="radioLabel" for="no">No</label>
					<span class="error">${ requestScope.form.errors['newCustomer'] }</span>
					<br class="stopFloat" />
					<br/>
					
					<div id="existingCustomer" <c:if test='${ !requestScope.form.noChecked }'>class="hidden"</c:if>>
						<select name="customerKey">
							<option>Select a customer:</option>
							
							<c:forEach items="${ sessionScope.customers }" var="customer">
								<option <c:if test='${ requestScope.form.customerKey == customer.key }'>selected</c:if> value="${ customer.key }">
									${ customer.value.firstName } ${ customer.value.lastName }
								</option>
							</c:forEach>
						</select>
						
						<span class="error">${ requestScope.form.errors['customerKey'] }</span>
					</div>
					
					<div id="newCustomer" <c:if test='${ !requestScope.form.yesChecked }'>class="hidden"</c:if>>
						<c:import url="/inc/inc_customer_form.jsp" />
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Order details</legend>
					
					<label for="orderDate">Date <span class="required">*</span></label>
					<input type="text" id="orderDate" name="orderDate" value='<c:out value="${ requestScope.order.date }" />' size="20" maxlength="20" disabled />
					<span class="error">${ requestScope.form.errors['orderDate'] }</span>
					<br />
					
					<label for="orderAmount">Amount <span class="required">*</span></label>
					<input type="text" id="orderAmount" name="orderAmount" value='<c:out value="${ requestScope.order.amount }" />' size="20" maxlength="20" />
					<span class="error">${ requestScope.form.errors['orderAmount'] }</span>
					<br />
					
					<label for="orderPaymentMethod">Payment method <span class="required">*</span></label>
					<input type="text" id="orderPaymentMethod" name="orderPaymentMethod" value='<c:out value="${ requestScope.order.paymentMethod }" />' size="20" maxlength="20" />
					<span class="error">${ requestScope.form.errors['orderPaymentMethod'] }</span>
					<br />
					
					<label for="orderPaymentStatus">Payment status</label>
					<input type="text" id="orderPaymentStatus" name="orderPaymentStatus" value='<c:out value="${ requestScope.order.paymentStatus }" />' size="20" maxlength="20" />
					<span class="error">${ requestScope.form.errors['orderPaymentStatus'] }</span>
					<br />
					
					<label for="orderShippingMode">Shipping mode <span class="required">*</span></label>
					<input type="text" id="orderShippingMode" name="orderShippingMode" value='<c:out value="${ requestScope.order.shippingMode }" />' size="20" maxlength="20" />
					<span class="error">${ requestScope.form.errors['orderShippingMode'] }</span>
					<br />
					
					<label for="orderDeliveryStatus">Delivery status</label>
					<input type="text" id="orderDeliveryStatus" name="orderDeliveryStatus" value='<c:out value="${ requestScope.order.deliveryStatus }" />' size="20" maxlength="20" />
					<span class="error">${ requestScope.form.errors['orderDeliveryStatus'] }</span>
					<br />
				</fieldset>
				
				<p class="info">${ requestScope.form.result }</p>
				
				<input type="submit" value="Create" />
				<input type="reset" value="Reset" /> <br />
			</form>
		</div>
		
		<script src="<c:url value="/inc/createOrder.js" />"></script>
	</body>
</html>
