package com.sdzee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;
import com.sdzee.dao.impl.DAOFactory;

public class CustomerDeleteController extends HttpServlet {
	
	public static final String SESS_ATT_CUSTOMERS = "customers";
	public static final String PARAM_CUSTOMER_KEY = "customerKey";
	
	public static final String VIEW = "/listCustomers";
	
	private static final long serialVersionUID = -2162738900489872154L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Customer> customers = (Map<String, Customer>) req.getSession().getAttribute(SESS_ATT_CUSTOMERS);
		String customerKey = req.getParameter(PARAM_CUSTOMER_KEY);
		
		if (customers != null)
		{
			customers.remove(customerKey);
			
			try
			{
				DAOFactory.getInstance().getCustomerDao().delete(Long.valueOf(customerKey));
			}
			catch (NumberFormatException ignore)
			{}
		}
		
		resp.sendRedirect(req.getContextPath() + VIEW);
	}

}
