package com.sdzee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Order;
import com.sdzee.dao.OrderDao;
import com.sdzee.dao.base.DAOException;
import com.sdzee.dao.impl.DAOFactory;

public class OrderDeleteController extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daoFactory";
	
	public static final String SESS_ATT_ORDERS = "orders";
	public static final String PARAM_ORDER_KEY = "orderKey";
	
	public static final String VIEW = "/listOrders";
	
	private OrderDao orderDao;
	
	private static final long serialVersionUID = -7613060205257533629L;
	
	@Override
	public void init() throws ServletException {
		
		this.orderDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getOrderDao();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<Long, Order> orders = (Map<Long, Order>) req.getSession().getAttribute(SESS_ATT_ORDERS);
		String orderKey = req.getParameter(PARAM_ORDER_KEY);
		
		if (orders != null)
		{
			try
			{
				Long orderKeyAsLong = Long.valueOf(orderKey);
				
				orders.remove(orderKeyAsLong);
				
				try
				{
					orderDao.delete(orderKeyAsLong);
				}
				catch (DAOException daoe)
				{
					daoe.printStackTrace();
				}
			}
			catch (NumberFormatException ignore)
			{}
		}
		
		resp.sendRedirect(req.getContextPath() + VIEW);
	}

}
