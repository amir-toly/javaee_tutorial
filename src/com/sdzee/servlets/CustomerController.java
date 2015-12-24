package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;

public class CustomerController extends HttpServlet {
	
	public static final String PARAM_LAST_NAME = "customerLastName";
	public static final String PARAM_FIRST_NAME = "customerFirstName";
	public static final String PARAM_ADDRESS = "customerAddress";
	public static final String PARAM_PHONE_NUMBER = "customerPhoneNumber";
	public static final String PARAM_EMAIL_ADDRESS = "customerEmailAddress";
	
	public static final String ATT_MSG = "msg";
	public static final String ATT_CUSTOMER = "customer";
	
	public static final String VIEW = "/displayCustomer.jsp";

	private static final long serialVersionUID = 3957764416980367802L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String msg = "Customer created successfully!";
		
		/* Create bean */
		Customer customer = new Customer();
		/* Initialize properties */
		customer.setLastName(req.getParameter(PARAM_LAST_NAME));
		customer.setFirstName(req.getParameter(PARAM_FIRST_NAME));
		customer.setAddress(req.getParameter(PARAM_ADDRESS));
		customer.setPhoneNumber(req.getParameter(PARAM_PHONE_NUMBER));
		customer.setEmail(req.getParameter(PARAM_EMAIL_ADDRESS));
		
		/* Validate fields */
		if (
				isBlank(customer.getLastName()) ||
				isBlank(customer.getAddress()) ||
				isBlank(customer.getPhoneNumber())
		)
		{
			msg =
					"Error - Required fields are missing. " + "<br />" +
					"<a href='createCustomer.jsp'>Click here</a> " +
					"to try again.";
		}
		
		/* Save bean and message in req object */
		req.setAttribute(ATT_MSG, msg);
		req.setAttribute(ATT_CUSTOMER, customer);
		
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
