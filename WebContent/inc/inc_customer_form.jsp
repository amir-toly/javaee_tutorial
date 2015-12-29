<%@ page pageEncoding="UTF-8" %>
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
