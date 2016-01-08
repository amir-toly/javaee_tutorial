package com.sdzee.forms;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.Customer;
import com.sdzee.forms.base.BaseForm;

public final class CustomerForm extends BaseForm {
	
	private static final String PARAM_LAST_NAME = "customerLastName";
	private static final String PARAM_FIRST_NAME = "customerFirstName";
	private static final String PARAM_ADDRESS = "customerAddress";
	private static final String PARAM_PHONE_NUMBER = "customerPhoneNumber";
	private static final String PARAM_EMAIL_ADDRESS = "customerEmailAddress";
	
	public Customer createCustomer(HttpServletRequest request) {
		
		String lastName = getParamValue(request, PARAM_LAST_NAME);
		String firstName = getParamValue(request, PARAM_FIRST_NAME);
		String address = getParamValue(request, PARAM_ADDRESS);
		String phoneNumber = getParamValue(request, PARAM_PHONE_NUMBER);
		String emailAddress = getParamValue(request, PARAM_EMAIL_ADDRESS);
		
		Customer customer = new Customer();
		
		try
		{
			validateLastName(lastName);
		}
		catch (Exception e)
		{
			setError(PARAM_LAST_NAME, e.getMessage());
		}
		customer.setLastName(lastName);
		
		try
		{
			validateFirstName(firstName);
		}
		catch (Exception e)
		{
			setError(PARAM_FIRST_NAME, e.getMessage());
		}
		customer.setFirstName(firstName);
		
		try
		{
			validateAddress(address);
		}
		catch (Exception e)
		{
			setError(PARAM_ADDRESS, e.getMessage());
		}
		customer.setAddress(address);
		
		try
		{
			validatePhoneNumber(phoneNumber);
		}
		catch (Exception e)
		{
			setError(PARAM_PHONE_NUMBER, e.getMessage());
		}
		customer.setPhoneNumber(phoneNumber);
		
		try
		{
			validateEmail(emailAddress);
		}
		catch (Exception e)
		{
			setError(PARAM_EMAIL_ADDRESS, e.getMessage());
		}
		customer.setEmail(emailAddress);
		
		if (errors.isEmpty())
		{
			result = "Customer created successfully!";
		}
		else
		{
			result = "Customer not created.";
		}
		
		return customer;
	}
	
	private void validateLastName(String lastName) throws Exception {
		
		validateTwoCharactersLongField(lastName, "last name");
	}
	
	private void validateFirstName(String firstName) throws Exception {
		
		if (firstName != null)
		{
			validateTwoCharactersLongField(firstName, "first name");
		}
	}
	
	private void validateAddress(String address) throws Exception {
		
		if (address == null || address.length() < 10)
		{
			throw new Exception("The address must contain at least 10 characters.");
		}
	}
	
	private void validatePhoneNumber(String phoneNumber) throws Exception {
		
		if (phoneNumber == null || !phoneNumber.matches("\\d{4,}"))
		{
			throw new Exception("The phone number must contain numbers (at least 4) only.");
		}
	}
	
	private void validateEmail(String email) throws Exception {
		
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
		{
			throw new Exception("The email address must be valid.");
		}
	}
}
