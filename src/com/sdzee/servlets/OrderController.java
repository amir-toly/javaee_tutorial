package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Order;
import com.sdzee.forms.OrderForm;

public class OrderController extends HttpServlet {
	
	public static final String ATT_FORM = "form";
	public static final String ATT_CUSTOMER = "customer";
	public static final String ATT_ORDER = "order";
	
	public static final String VIEW_FORM = "/WEB-INF/createOrder.jsp";
	public static final String VIEW_RESULT = "/WEB-INF/displayOrder.jsp";

	private static final long serialVersionUID = 2592998444053332359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Display order creation page */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Create form */
		OrderForm form = new OrderForm();
		
		/* Retrieve bean from form processing */
		Order order = form.createOrder(req);
		
		/* Save beans and form in req object */
		req.setAttribute(ATT_FORM, form);
		req.setAttribute(ATT_CUSTOMER, order.getCustomer());
		req.setAttribute(ATT_ORDER, order);
		
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
