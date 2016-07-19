package com.sdzee.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.sdzee.dao.CustomerDao;
import com.sdzee.dao.OrderDao;
import com.sdzee.dao.base.DAOConfigurationException;

public class DAOFactory {

	private static final String PROPERTIES_FILE = "/com/sdzee/dao/base/dao.properties";
	private static final String PROP_URL = "url";
	private static final String PROP_DRIVER = "driver";
	private static final String PROP_USERNAME = "username";
	private static final String PROP_PASSWORD = "password";
	
	private String url;
	private String username;
	private String password;
	
	DAOFactory(String url, String username, String password) {
		
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		
		Properties properties = new Properties();
		String url;
		String driver;
		String username;
		String password;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
		
		if (propertiesFile == null)
		{
			throw new DAOConfigurationException("The properties file " + PROPERTIES_FILE + " can not be found.");
		}
		
		try
		{
			properties.load(propertiesFile);
			url = properties.getProperty(PROP_URL);
			driver = properties.getProperty(PROP_DRIVER);
			username = properties.getProperty(PROP_USERNAME);
			password = properties.getProperty(PROP_PASSWORD);
		}
		catch (IOException ioe)
		{
			throw new DAOConfigurationException("Can not load the properties file " + PROPERTIES_FILE, ioe);
		}
		
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException cnfe)
		{
			throw new DAOConfigurationException("Database driver can not be found in classpath.", cnfe);
		}
		
		DAOFactory instance = new DAOFactory(url, username, password);
		
		return instance;
	}
	
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public CustomerDao getCustomerDao() {
		return new CustomerDaoImpl(this);
	}
	
	public OrderDao getOrderDao() {
		return new OrderDaoImpl(this);
	}
}
