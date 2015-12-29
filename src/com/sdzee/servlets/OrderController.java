package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;

public class OrderController extends HttpServlet {
	
	public static final String PARAM_LAST_NAME = "customerLastName";
	public static final String PARAM_FIRST_NAME = "customerFirstName";
	public static final String PARAM_ADDRESS = "customerAddress";
	public static final String PARAM_PHONE_NUMBER = "customerPhoneNumber";
	public static final String PARAM_EMAIL_ADDRESS = "customerEmailAddress";
	public static final String PARAM_AMOUNT = "orderAmount";
	public static final String PARAM_PAYMENT_METHOD = "orderPaymentMethod";
	public static final String PARAM_PAYMENT_STATUS = "orderPaymentStatus";
	public static final String PARAM_SHIPPING_MODE = "orderShippingMode";
	public static final String PARAM_DELIVERY_STATUS = "orderDeliveryStatus";
	
	public static final String ATT_MSG = "msg";
	public static final String ATT_ORDER = "order";
	public static final String ATT_ERROR = "error";
	
	public static final String VIEW = "/displayOrder.jsp";
	
	public static final String DATE_FORMAT = "dd/MM/yyyy HH:MM:ss";

	private static final long serialVersionUID = 2592998444053332359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String msg = "Order created successfully!";
		boolean error = false;
		
		/* Create beans */
		Customer customer = new Customer();
		Order order = new Order();
		
		/* Initialize properties */
		customer.setLastName(req.getParameter(PARAM_LAST_NAME));
		customer.setFirstName(req.getParameter(PARAM_FIRST_NAME));
		customer.setAddress(req.getParameter(PARAM_ADDRESS));
		customer.setPhoneNumber(req.getParameter(PARAM_PHONE_NUMBER));
		customer.setEmail(req.getParameter(PARAM_EMAIL_ADDRESS));
		
		order.setDate(DateTime.now().toString(DATE_FORMAT));
		
		try
		{
			order.setAmount(Double.valueOf(req.getParameter(PARAM_AMOUNT)));
		}
		catch (NullPointerException | NumberFormatException exception)
		{
			order.setAmount(-1);
		}
		
		order.setPaymentMethod(req.getParameter(PARAM_PAYMENT_METHOD));
		order.setPaymentStatus(req.getParameter(PARAM_PAYMENT_STATUS));
		order.setShippingMode(req.getParameter(PARAM_SHIPPING_MODE));
		order.setDeliveryStatus(req.getParameter(PARAM_DELIVERY_STATUS));
		order.setCustomer(customer);
		
		/* Validate fields */
		if (
				isBlank(customer.getLastName()) ||
				isBlank(customer.getAddress()) ||
				isBlank(customer.getPhoneNumber()) ||
				order.getAmount() == -1 ||
				isBlank(order.getPaymentMethod()) ||
				isBlank(order.getShippingMode())
		)
		{
			msg =
					"Error - Required fields are missing. " + "<br />" +
					"<a href='createOrder.jsp'>Click here</a> " +
					"to try again.";
			error = true;
		}
		
		/* Save bean and message in req object */
		req.setAttribute(ATT_MSG, msg);
		req.setAttribute(ATT_ORDER, order);
		req.setAttribute(ATT_ERROR, error);
		
		/* Transmit req/resp to JSP */
		this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
	}
	
	/**
	 * isBlank
	 * @param stringToCheck
	 * @return boolean
	 */
	private boolean isBlank(String stringToCheck) {
		
		return stringToCheck == null || stringToCheck.trim().isEmpty();
	}

}
