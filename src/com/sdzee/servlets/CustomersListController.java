package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomersListController extends HttpServlet {
	
	public static final String VIEW = "/WEB-INF/listCustomers.jsp";

	private static final long serialVersionUID = -2197887849985318893L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Display customers list page */
		this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
	}

}
