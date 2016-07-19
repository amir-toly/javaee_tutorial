package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;
import com.sdzee.dao.CustomerDao;
import com.sdzee.dao.impl.DAOFactory;
import com.sdzee.forms.CustomerForm;
import com.sdzee.servlets.util.AvoidDuplication;

public class CustomerController extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daoFactory";
	
	public static final String ATT_FORM = "form";
	public static final String ATT_CUSTOMER = "customer";
	
	public static final String VIEW_FORM = "/WEB-INF/createCustomer.jsp";
	public static final String VIEW_RESULT = "/WEB-INF/displayCustomer.jsp";
	
	private CustomerDao customerDao;

	private static final long serialVersionUID = 3957764416980367802L;
	
	@Override
	public void init() throws ServletException {
		
		this.customerDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCustomerDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Display customer creation page */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Create form */
		CustomerForm form = new CustomerForm(customerDao);
		
		/* Retrieve bean from form processing */
		Customer customer = form.createCustomer(req, AvoidDuplication.getPath(this));
		
		/* Save bean and form in req object */
		req.setAttribute(ATT_FORM, form);
		req.setAttribute(ATT_CUSTOMER, customer);
		
		/* Transmit req/resp to JSP */
		if (form.getErrors().isEmpty())
		{
			AvoidDuplication.saveCustomerInSession(customer, req);
			
			this.getServletContext().getRequestDispatcher(VIEW_RESULT).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
		}
	}

}
