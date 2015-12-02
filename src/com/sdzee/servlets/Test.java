package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {

	private static final long serialVersionUID = 5562599200066279660L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String paramAuthor = "URL request parameter added from the servlet: " + req.getParameter("author") + ".";
		String message = "Variables transmission: OK ! " + paramAuthor;
		
		req.setAttribute("test", message);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
	}

}
