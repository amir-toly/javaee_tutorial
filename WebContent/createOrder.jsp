<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Order creation</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	<body>
		<div>
			<form method="get" action="createOrder">
				<fieldset>
					<legend>Customer details</legend>
					
					<label for="customerLastName">Last name <span class="required">*</span></label>
					<input type="text" id="customerLastName" name="customerLastName" value="" size="20" maxlength="20" />
					<br />
					
					<label for="customerFirstName">First name</label>
					<input type="text" id="customerFirstName" name="customerFirstName" value="" size="20" maxlength="20" />
					<br />
					
					<label for="customerAddress">Shipping address <span class="required">*</span></label>
					<input type="text" id="customerAddress" name="customerAddress" value="" size="20" maxlength="60" />
					<br />
					
					<label for="customerPhoneNumber">Phone number <span class="required">*</span></label>
					<input type="text" id="customerPhoneNumber" name="customerPhoneNumber" value="" size="20" maxlength="20" />
					<br />
					
					<label for="customerEmailAddress">Email address</label>
					<input type="email" id="customerEmailAddress" name="customerEmailAddress" value="" size="20" maxlength="60" />
					<br />
				</fieldset>
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
