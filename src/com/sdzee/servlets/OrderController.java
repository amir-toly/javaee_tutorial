package com.sdzee.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Order;
import com.sdzee.dao.CustomerDao;
import com.sdzee.dao.OrderDao;
import com.sdzee.dao.impl.DAOFactory;
import com.sdzee.forms.OrderForm;
import com.sdzee.servlets.util.AvoidDuplication;

public class OrderController extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daoFactory";
	
	public static final String ATT_FORM = "form";
	public static final String ATT_ORDER = "order";
	private static final String SESS_ATT_ORDERS = "orders";
	
	public static final String VIEW_FORM = "/WEB-INF/createOrder.jsp";
	public static final String VIEW_RESULT = "/WEB-INF/displayOrder.jsp";
	
	private OrderDao orderDao;
	private CustomerDao customerDao;

	private static final long serialVersionUID = 2592998444053332359L;
	
	@Override
	public void init() throws ServletException {
		
		DAOFactory daoFactory = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY));
		
		this.orderDao = daoFactory.getOrderDao();
		this.customerDao = daoFactory.getCustomerDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Display order creation page */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Create form */
		OrderForm form = new OrderForm(orderDao, customerDao);
		
		/* Retrieve bean from form processing */
		Order order = form.createOrder(req, AvoidDuplication.getPath(this));
		
		/* Save bean and form in req object */
		req.setAttribute(ATT_FORM, form);
		req.setAttribute(ATT_ORDER, order);
		
		/* Transmit req/resp to JSP */
		if (form.getErrors().isEmpty())
		{
			AvoidDuplication.saveCustomerInSession(order.getCustomer(), req);
			
			HttpSession session = req.getSession();
			Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute(SESS_ATT_ORDERS);
			
			if (orders == null)
			{
				orders = new HashMap<Long, Order>();
				session.setAttribute(SESS_ATT_ORDERS, orders);
			}
			
			orders.put(order.getId(), order);
			
			this.getServletContext().getRequestDispatcher(VIEW_RESULT).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(req, resp);
		}
	}

}
