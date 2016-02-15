package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrdersListController extends HttpServlet {
	
	public static final String VIEW = "/WEB-INF/listOrders.jsp";

	private static final long serialVersionUID = 5087895134109569481L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Display orders list page */
		this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
	}

}
