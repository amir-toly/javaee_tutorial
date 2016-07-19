package com.sdzee.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdzee.dao.impl.DAOFactory;

public class DAOFactoryInitialization implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "daoFactory";
	
	private DAOFactory daoFactory;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		// Nothing to do when context is destroyed
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext servletContext = event.getServletContext();
		
		this.daoFactory = DAOFactory.getInstance();
		
		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
	}
}
