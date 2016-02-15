package com.sdzee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;

public class CustomerDeleteController extends HttpServlet {
	
	public static final String SESS_ATT_CUSTOMERS = "customers";
	public static final String PARAM_CUSTOMER_IDX = "customerIdx";
	
	public static final String VIEW = "/listCustomers";
	
	private static final long serialVersionUID = -2162738900489872154L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Customer> customers = (List<Customer>) req.getSession().getAttribute(SESS_ATT_CUSTOMERS);
		
		String customerIdxAsString = req.getParameter(PARAM_CUSTOMER_IDX);
		int customerIdx = -1;
		
		try
		{
			customerIdx = Integer.parseInt(customerIdxAsString);
		}
		catch (NumberFormatException nfe)
		{
			// customerIdx variable has already been set to -1 by default
		}
		
		if (customerIdx >= 0 && customerIdx < customers.size())
		{
			customers.remove(customerIdx);
		}
		
		resp.sendRedirect(req.getContextPath() + VIEW);
	}

}
