package com.sdzee.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;
import com.sdzee.forms.base.BaseForm;

public final class OrderForm extends BaseForm {
	
	private static final String PARAM_NEW_CUSTOMER = "newCustomer";
	private static final String PARAM_CUSTOMER_IDX = "customerIdx";
	private static final String PARAM_AMOUNT = "orderAmount";
	private static final String PARAM_PAYMENT_METHOD = "orderPaymentMethod";
	private static final String PARAM_PAYMENT_STATUS = "orderPaymentStatus";
	private static final String PARAM_SHIPPING_MODE = "orderShippingMode";
	private static final String PARAM_DELIVERY_STATUS = "orderDeliveryStatus";
	
	private static final String SESS_ATT_CUSTOMERS = "customers";
	
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:MM:ss";
	private static final String NEW_CUSTOMER_VALUE_YES = "yes";
	private static final String NEW_CUSTOMER_VALUE_NO = "no";
	
	private boolean yesChecked = false;
	private boolean noChecked = false;
	private int customerIdx = 1;
	
	@SuppressWarnings("unchecked")
	public Order createOrder(HttpServletRequest request) {
		
		String newCustomer = getParamValue(request, PARAM_NEW_CUSTOMER);
		String customerIdxAsString = getParamValue(request, PARAM_CUSTOMER_IDX);
		
		Customer customer = null;
		
		try
		{
			validateNewCustomer(newCustomer);
		}
		catch (Exception e)
		{
			setError(PARAM_NEW_CUSTOMER, e.getMessage());
		}
		
		if (noChecked)
		{
			try
			{
				List<Customer> customers = (List<Customer>) request.getSession().getAttribute(SESS_ATT_CUSTOMERS);
				
				validateCustomerIdx(customerIdxAsString, customers);
				
				customer = customers.get(customerIdx);
			}
			catch (Exception e)
			{
				setError(PARAM_CUSTOMER_IDX, e.getMessage());
			}
		}
		else if (yesChecked)
		{
		/* Build OrderForm from CustomerForm */
		CustomerForm customerForm = new CustomerForm();
		customer = customerForm.createCustomer(request);
		errors.putAll(customerForm.getErrors());
		}
		
		String amount = getParamValue(request, PARAM_AMOUNT);
		String paymentMethod = getParamValue(request, PARAM_PAYMENT_METHOD);
		String paymentStatus = getParamValue(request, PARAM_PAYMENT_STATUS);
		String shippingMode = getParamValue(request, PARAM_SHIPPING_MODE);
		String deliveryStatus = getParamValue(request, PARAM_DELIVERY_STATUS);
		
		Order order = new Order();
		
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
		catch (Exception e)
		{
			setError(PARAM_PAYMENT_METHOD, e.getMessage());
		}
		order.setPaymentMethod(paymentMethod);
		
		try
		{
			validatePaymentStatus(paymentStatus);
		}
		catch (Exception e)
		{
			setError(PARAM_PAYMENT_STATUS, e.getMessage());
		}
		order.setPaymentStatus(paymentStatus);
		
		try
		{
			validateShippingMode(shippingMode);
		}
		catch (Exception e)
		{
			setError(PARAM_SHIPPING_MODE, e.getMessage());
		}
		order.setShippingMode(shippingMode);
		
		try
		{
			validateDeliveryStatus(deliveryStatus);
		}
		catch (Exception e)
		{
			setError(PARAM_DELIVERY_STATUS, e.getMessage());
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
	
	private void validateNewCustomer(String newCustomer) throws Exception {
		
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
			throw new Exception("The new customer attribute must be set to yes or no.");
		}
	}
	
	private void customerNotFromListException() throws Exception {
		
		throw new Exception("The selected customer must come from the list.");
	}
	
	private void validateCustomerIdx(String customerIdxAsString, List<Customer> customers) throws Exception {
		
		try
		{
			customerIdx = Integer.parseInt(customerIdxAsString);
			
			if (customers == null || customers.isEmpty() || customerIdx < 0 || customerIdx >= customers.size())
			{
				customerNotFromListException();
			}
		}
		catch (NumberFormatException nfe)
		{
			customerNotFromListException();
		}
	}
	
	private void validateAmount(String amount) throws Exception {
		
		if (amount == null || !amount.matches("\\d+(\\.\\d+)?"))
		{
			throw new Exception("The amount must be a positive decimal number.");
		}
	}
	
	private void validatePaymentMethod(String paymentMethod) throws Exception {
		
		validateTwoCharactersLongField(paymentMethod, "payment method");
	}
	
	private void validatePaymentStatus(String paymentStatus) throws Exception {
		
		if (paymentStatus != null)
		{
			validateTwoCharactersLongField(paymentStatus, "payment status");
		}
	}
	
	private void validateShippingMode(String shippingMode) throws Exception {
		
		validateTwoCharactersLongField(shippingMode, "shipping mode");
	}
	
	private void validateDeliveryStatus(String deliveryStatus) throws Exception {
		
		if (deliveryStatus != null)
		{
			validateTwoCharactersLongField(deliveryStatus, "delivery status");
		}
	}
	
	public boolean isYesChecked() {
		return yesChecked;
	}
	
	public boolean isNoChecked() {
		return noChecked;
	}

	public int getCustomerIdx() {
		return customerIdx;
	}
}
