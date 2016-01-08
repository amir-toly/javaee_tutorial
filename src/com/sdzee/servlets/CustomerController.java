package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;
import com.sdzee.forms.CustomerForm;

public class CustomerController extends HttpServlet {
	
	public static final String ATT_FORM = "form";
	public static final String ATT_CUSTOMER = "customer";
	
	public static final String VIEW_FORM = "/WEB-INF/createCustomer.jsp";
	public static final String VIEW_RESULT = "/WEB-INF/displayCustomer.jsp";

	private static final long serialVersionUID = 3957764416980367802L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Display customer creation page */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Create form */
		CustomerForm form = new CustomerForm();
		
		/* Retrieve bean from form processing */
		Customer customer = form.createCustomer(req);
		
		/* Save bean and form in req object */
		req.setAttribute(ATT_FORM, form);
		req.setAttribute(ATT_CUSTOMER, customer);
		
		/* Transmit req/resp to JSP */
		if (form.getErrors().isEmpty())
		{
			this.getServletContext().getRequestDispatcher(VIEW_RESULT).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
		}
	}

}
