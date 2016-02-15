package com.sdzee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Order;

public class OrderDeleteController extends HttpServlet {
	
	public static final String SESS_ATT_ORDERS = "orders";
	public static final String PARAM_ORDER_IDX = "orderIdx";
	
	public static final String VIEW = "/listOrders";
	
	private static final long serialVersionUID = -7613060205257533629L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Order> orders = (List<Order>) req.getSession().getAttribute(SESS_ATT_ORDERS);
		
		String orderIdxAsString = req.getParameter(PARAM_ORDER_IDX);
		int orderIdx = -1;
		
		try
		{
			orderIdx = Integer.parseInt(orderIdxAsString);
		}
		catch (NumberFormatException nfe)
		{
			// orderIdx variable has already been set to -1 by default
		}
		
		if (orderIdx >= 0 && orderIdx < orders.size())
		{
			orders.remove(orderIdx);
		}
		
		resp.sendRedirect(req.getContextPath() + VIEW);
	}

}
