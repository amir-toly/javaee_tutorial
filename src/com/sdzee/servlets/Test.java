package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Coyote;

public class Test extends HttpServlet {

	private static final long serialVersionUID = 5562599200066279660L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* Create and initialize message */
		String paramAuthor = "URL request parameter added from the servlet: " + req.getParameter("author") + ".";
		String message = "Variables transmission: OK ! " + paramAuthor;
		
		/* Create bean */
		Coyote firstBean = new Coyote();
		/* Initialize properties */
		firstBean.setLastName("Coyote");
		firstBean.setFirstName("Wile E.");
		
		/* Save bean and message in req object */
		req.setAttribute("test", message);
		req.setAttribute("coyote", firstBean);
		
		/* Transmit req/resp to JSP */
		this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
	}

}
