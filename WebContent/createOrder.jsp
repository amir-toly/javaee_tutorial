<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Order creation</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	<body>
		<%@ include file="inc/menu.jsp" %>
		<div>
			<form method="get" action="createOrder">
				<c:import url="inc/inc_customer_form.jsp" />
				
				<fieldset>
					<legend>Order details</legend>
					
					<label for="orderDate">Date <span class="required">*</span></label>
					<input type="text" id="orderDate" name="orderDate" value="" size="20" maxlength="20" disabled />
					<br />
					
					<label for="orderAmount">Amount <span class="required">*</span></label>
					<input type="text" id="orderAmount" name="orderAmount" value="" size="20" maxlength="20" />
					<br />
					
					<label for="orderPaymentMethod">Payment method <span class="required">*</span></label>
					<input type="text" id="orderPaymentMethod" name="orderPaymentMethod" value="" size="20" maxlength="20" />
					<br />
					
					<label for="orderPaymentStatus">Payment status</label>
					<input type="text" id="orderPaymentStatus" name="orderPaymentStatus" value="" size="20" maxlength="20" />
					<br />
					
					<label for="orderShippingMode">Shipping mode <span class="required">*</span></label>
					<input type="text" id="orderShippingMode" name="orderShippingMode" value="" size="20" maxlength="20" />
					<br />
					
					<label for="orderDeliveryStatus">Delivery status</label>
					<input type="text" id="orderDeliveryStatus" name="orderDeliveryStatus" value="" size="20" maxlength="20" />
					<br />
				</fieldset>
				<input type="submit" value="Create" />
				<input type="reset" value="Reset" /> <br />
			</form>
		</div>
	</body>
</html>
