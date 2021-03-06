package com.sdzee.servlets.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Customer;

public class AvoidDuplication {
	
	private static final String SESS_ATT_CUSTOMERS = "customers";
	
	private static final String PATH = "path";

	@SuppressWarnings("unchecked")
	public static void saveCustomerInSession(Customer customer, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		Map<Long,Customer> customers = (Map<Long,Customer>) session.getAttribute(SESS_ATT_CUSTOMERS);
		
		if (customers == null)
		{
			customers = new HashMap<Long, Customer>();
			session.setAttribute(SESS_ATT_CUSTOMERS, customers);
		}
		
		customers.put(customer.getId(), customer);
	}
	
	public static String getPath(HttpServlet httpServlet) {
		
		return httpServlet.getInitParameter(PATH);
	}
}
