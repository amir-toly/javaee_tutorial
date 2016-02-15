package com.sdzee.servlets.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Customer;

public class AvoidDuplication {
	
	private static final String SESS_ATT_CUSTOMERS = "customers";

	@SuppressWarnings("unchecked")
	public static void saveCustomerInSession(Customer customer, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		List<Customer> customers = (List<Customer>) session.getAttribute(SESS_ATT_CUSTOMERS);
		
		if (customers == null)
		{
			customers = new ArrayList<Customer>();
			session.setAttribute(SESS_ATT_CUSTOMERS, customers);
		}
		
		customers.add(customer);
	}
}
