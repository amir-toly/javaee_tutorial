package com.sdzee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Customer;
import com.sdzee.dao.CustomerDao;
import com.sdzee.dao.base.DAOException;
import com.sdzee.dao.impl.DAOFactory;

public class CustomerDeleteController extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daoFactory";
	
	public static final String SESS_ATT_CUSTOMERS = "customers";
	public static final String PARAM_CUSTOMER_KEY = "customerKey";
	
	public static final String VIEW = "/listCustomers";
	
	private CustomerDao customerDao;
	
	private static final long serialVersionUID = -2162738900489872154L;
	
	@Override
	public void init() throws ServletException {
		
		this.customerDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCustomerDao();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<Long, Customer> customers = (Map<Long, Customer>) req.getSession().getAttribute(SESS_ATT_CUSTOMERS);
		String customerKey = req.getParameter(PARAM_CUSTOMER_KEY);
		
		if (customers != null)
		{
			try
			{
				Long customerKeyAsLong = Long.valueOf(customerKey);
				
				customers.remove(customerKeyAsLong);
				
				try
				{
					customerDao.delete(customerKeyAsLong);
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
