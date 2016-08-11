package com.sdzee.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;
import com.sdzee.dao.CustomerDao;
import com.sdzee.dao.OrderDao;
import com.sdzee.dao.impl.DAOFactory;

public class DatabaseToSessionFilter implements Filter {

	private static final String CONF_DAO_FACTORY = "daoFactory";
	
	private static final String SESS_ATT_CUSTOMERS = "customers";
	private static final String SESS_ATT_ORDERS = "orders";
	
	private CustomerDao customerDao;
	private OrderDao orderDao;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		DAOFactory daoFactory = ((DAOFactory) config.getServletContext().getAttribute(CONF_DAO_FACTORY));
		
		this.customerDao = daoFactory.getCustomerDao();
		this.orderDao = daoFactory.getOrderDao();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		
		if (session.isNew())
		{
			Map<Long, Customer> customersMap = new HashMap<Long, Customer>();
			Map<Long, Order> ordersMap = new HashMap<Long, Order>();
			List<Customer> customers = customerDao.findAll();
			List<Order> orders = orderDao.findAll();
			
			for (Customer customer : customers) {
				customersMap.put(customer.getId(), customer);
			}
			
			for (Order order : orders) {
				ordersMap.put(order.getId(), order);
			}
			
			session.setAttribute(SESS_ATT_CUSTOMERS, customersMap);
			session.setAttribute(SESS_ATT_ORDERS, ordersMap);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
