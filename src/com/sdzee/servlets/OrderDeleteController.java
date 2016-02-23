package com.sdzee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Order;

public class OrderDeleteController extends HttpServlet {
	
	public static final String SESS_ATT_ORDERS = "orders";
	public static final String PARAM_ORDER_KEY = "orderKey";
	
	public static final String VIEW = "/listOrders";
	
	private static final long serialVersionUID = -7613060205257533629L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Order> orders = (Map<String, Order>) req.getSession().getAttribute(SESS_ATT_ORDERS);
		String orderKey = req.getParameter(PARAM_ORDER_KEY);
		
		if (orders != null)
		{
			orders.remove(orderKey);
		}
		
		resp.sendRedirect(req.getContextPath() + VIEW);
	}

}
