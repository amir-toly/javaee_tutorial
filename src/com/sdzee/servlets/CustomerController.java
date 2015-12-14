package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;

public class CustomerController extends HttpServlet {

	private static final long serialVersionUID = 3957764416980367802L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String msg = "Customer created successfully!";
		
		/* Create bean */
		Customer customer = new Customer();
		/* Initialize properties */
		customer.setLastName(req.getParameter("customerLastName"));
		customer.setFirstName(req.getParameter("customerFirstName"));
		customer.setAddress(req.getParameter("customerAddress"));
		customer.setPhoneNumber(req.getParameter("customerPhoneNumber"));
		customer.setEmail(req.getParameter("customerEmailAddress"));
		
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
		req.setAttribute("msg", msg);
		req.setAttribute("customer", customer);
		
		/* Transmit req/resp to JSP */
		this.getServletContext().getRequestDispatcher("/displayCustomer.jsp").forward(req, resp);
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
