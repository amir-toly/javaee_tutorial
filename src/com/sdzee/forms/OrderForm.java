package com.sdzee.forms;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;
import com.sdzee.forms.base.BaseForm;

public final class OrderForm extends BaseForm {
	
	private static final String PARAM_AMOUNT = "orderAmount";
	private static final String PARAM_PAYMENT_METHOD = "orderPaymentMethod";
	private static final String PARAM_PAYMENT_STATUS = "orderPaymentStatus";
	private static final String PARAM_SHIPPING_MODE = "orderShippingMode";
	private static final String PARAM_DELIVERY_STATUS = "orderDeliveryStatus";
	
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:MM:ss";
	
	public Order createOrder(HttpServletRequest request) {
		
		/* Build OrderForm from CustomerForm */
		CustomerForm customerForm = new CustomerForm();
		Customer customer = customerForm.createCustomer(request);
		errors.putAll(customerForm.getErrors());
		
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
}
