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

	private static final long serialVersionUID = 2592998444053332359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String msg = "Order created successfully!";
		
		/* Create beans */
		Customer customer = new Customer();
		Order order = new Order();
		
		/* Initialize properties */
		customer.setLastName(req.getParameter("customerLastName"));
		customer.setFirstName(req.getParameter("customerFirstName"));
		customer.setAddress(req.getParameter("customerAddress"));
		customer.setPhoneNumber(req.getParameter("customerPhoneNumber"));
		customer.setEmail(req.getParameter("customerEmailAddress"));
		
		order.setDate(DateTime.now().toString("dd/MM/yyyy HH:MM:ss"));
		
		try
		{
			order.setAmount(Double.valueOf(req.getParameter("orderAmount")));
		}
		catch (NullPointerException | NumberFormatException exception)
		{
			order.setAmount(-1);
		}
		
		order.setPaymentMethod(req.getParameter("orderPaymentMethod"));
		order.setPaymentStatus(req.getParameter("orderPaymentStatus"));
		order.setShippingMode(req.getParameter("orderShippingMode"));
		order.setDeliveryStatus(req.getParameter("orderDeliveryStatus"));
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
		}
		
		/* Save bean and message in req object */
		req.setAttribute("msg", msg);
		req.setAttribute("order", order);
		
		/* Transmit req/resp to JSP */
		this.getServletContext().getRequestDispatcher("/displayOrder.jsp").forward(req, resp);
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
