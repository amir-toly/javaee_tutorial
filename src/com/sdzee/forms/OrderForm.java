package com.sdzee.forms;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;
import com.sdzee.forms.base.BaseForm;

public final class OrderForm extends BaseForm {
	
	private static final String PARAM_NEW_CUSTOMER = "newCustomer";
	private static final String PARAM_CUSTOMER_KEY = "customerKey";
	private static final String PARAM_AMOUNT = "orderAmount";
	private static final String PARAM_PAYMENT_METHOD = "orderPaymentMethod";
	private static final String PARAM_PAYMENT_STATUS = "orderPaymentStatus";
	private static final String PARAM_SHIPPING_MODE = "orderShippingMode";
	private static final String PARAM_DELIVERY_STATUS = "orderDeliveryStatus";
	
	private static final String SESS_ATT_CUSTOMERS = "customers";
	
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:MM:ss";
	private static final String NEW_CUSTOMER_VALUE_YES = "newCustomer";
	private static final String NEW_CUSTOMER_VALUE_NO = "existingCustomer";
	
	private boolean yesChecked = false;
	private boolean noChecked = false;
	private String customerKey;
	
	@SuppressWarnings("unchecked")
	public Order createOrder(HttpServletRequest request, String path) {
		
		Map<String, Customer> customers = (Map<String, Customer>) request.getSession().getAttribute(SESS_ATT_CUSTOMERS);
		boolean customersInSession = false;
		
		if (customers != null && !customers.isEmpty())
		{
			customersInSession = true;
		}
		
		String amount = getParamValue(request, PARAM_AMOUNT);
		String paymentMethod = getParamValue(request, PARAM_PAYMENT_METHOD);
		String paymentStatus = getParamValue(request, PARAM_PAYMENT_STATUS);
		String shippingMode = getParamValue(request, PARAM_SHIPPING_MODE);
		String deliveryStatus = getParamValue(request, PARAM_DELIVERY_STATUS);
		
		Order order = new Order();
		Customer customer = null;
		
		if (customersInSession)
		{
			String newCustomer = getParamValue(request, PARAM_NEW_CUSTOMER);
			customerKey = getParamValue(request, PARAM_CUSTOMER_KEY);
			
			try
			{
				validateNewCustomer(newCustomer);
			}
			catch (FormValidationException fve)
			{
				setError(PARAM_NEW_CUSTOMER, fve.getMessage());
			}
			
			if (noChecked)
			{
				try
				{
					validateCustomerKey(customerKey, customers);
					
					customer = customers.get(customerKey);
				}
				catch (FormValidationException fve)
				{
					setError(PARAM_CUSTOMER_KEY, fve.getMessage());
				}
			}
		}
		
		if (!customersInSession || yesChecked)
		{
			/* Build OrderForm from CustomerForm */
			CustomerForm customerForm = new CustomerForm();
			customer = customerForm.createCustomer(request, path);
			errors.putAll(customerForm.getErrors());
		}
		
		order.setCustomer(customer);
		order.setDate(DateTime.now().toString(DATE_FORMAT));
		
		try
		{
			validateAmount(amount);
			// If the following line raises an exception, the amount attribute will be set to Java default double value
			order.setAmount(Double.parseDouble(amount));
		}
		catch (Exception e)
		{
			setError(PARAM_AMOUNT, e.getMessage());
		}
		
		try
		{
			validatePaymentMethod(paymentMethod);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_PAYMENT_METHOD, fve.getMessage());
		}
		order.setPaymentMethod(paymentMethod);
		
		try
		{
			validatePaymentStatus(paymentStatus);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_PAYMENT_STATUS, fve.getMessage());
		}
		order.setPaymentStatus(paymentStatus);
		
		try
		{
			validateShippingMode(shippingMode);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_SHIPPING_MODE, fve.getMessage());
		}
		order.setShippingMode(shippingMode);
		
		try
		{
			validateDeliveryStatus(deliveryStatus);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_DELIVERY_STATUS, fve.getMessage());
		}
		order.setDeliveryStatus(deliveryStatus);
		
		if (errors.isEmpty())
		{
			result = "Customer created successfully!";
		}
		else
		{
			result = "Customer not created.";
		}
		
		return order;
	}
	
	/**
	 * VALIDATION METHODS
	 */
	
	private void validateNewCustomer(String newCustomer) throws FormValidationException {
		
		if (NEW_CUSTOMER_VALUE_YES.equals(newCustomer))
		{
			yesChecked = true;
		}
		else if (NEW_CUSTOMER_VALUE_NO.equals(newCustomer))
		{
			noChecked = true;
		}
		else
		{
			throw new FormValidationException("The new customer attribute must be set to yes or no.");
		}
	}
	
	private void validateCustomerKey(String customerKey, Map<String, Customer> customers) throws FormValidationException {
		
		if (!customers.containsKey(customerKey))
		{
			throw new FormValidationException("The selected customer must come from the list.");
		}
	}
	
	private void validateAmount(String amount) throws FormValidationException {
		
		if (amount == null || !amount.matches("\\d+(\\.\\d+)?"))
		{
			throw new FormValidationException("The amount must be a positive decimal number.");
		}
	}
	
	private void validatePaymentMethod(String paymentMethod) throws FormValidationException {
		
		validateTwoCharactersLongField(paymentMethod, "payment method");
	}
	
	private void validatePaymentStatus(String paymentStatus) throws FormValidationException {
		
		if (paymentStatus != null)
		{
			validateTwoCharactersLongField(paymentStatus, "payment status");
		}
	}
	
	private void validateShippingMode(String shippingMode) throws FormValidationException {
		
		validateTwoCharactersLongField(shippingMode, "shipping mode");
	}
	
	private void validateDeliveryStatus(String deliveryStatus) throws FormValidationException {
		
		if (deliveryStatus != null)
		{
			validateTwoCharactersLongField(deliveryStatus, "delivery status");
		}
	}
	
	/**
	 * OTHER METHODS
	 */
	
	public boolean isYesChecked() {
		return yesChecked;
	}
	
	public boolean isNoChecked() {
		return noChecked;
	}

	public String getCustomerKey() {
		return customerKey;
	}
}
