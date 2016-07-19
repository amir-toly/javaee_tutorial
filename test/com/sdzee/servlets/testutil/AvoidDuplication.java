package com.sdzee.servlets.testutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;

public class AvoidDuplication {
	
	public static String[] customerFields = new String[] {
			"customerLastName",
			"customerFirstName",
			"customerAddress",
			"customerPhoneNumber",
			"customerEmailAddress",
			"customerPictureFile"
	};
	
	public static String[] orderFields = new String[] {
			"customerLastName",
			"customerFirstName",
			"customerAddress",
			"customerPhoneNumber",
			"customerEmailAddress",
			"customerPictureFile",
			"orderAmount",
			"orderPaymentMethod",
			"orderPaymentStatus",
			"orderShippingMode",
			"orderDeliveryStatus"
	};
	
	/**
	 * Returns the last inserted customer if customerId is null
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public static Customer getCustomerFromDb(String customerId) throws Exception {
		
		Customer lastInsertedCustomer = null;
		
		if (customerId == null)
		{
			customerId = "(SELECT MAX(id) FROM t_customer)";
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			throw new Exception("Database driver can not be found in classpath.", e);
		}
		
		String query = "SELECT * FROM t_customer WHERE id = " + customerId;
		String url = "jdbc:mysql://localhost:3306/sdzee";
		String user = "java";
		String password = "SdZ_eE";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next())
			{
				lastInsertedCustomer = new Customer();
				
				lastInsertedCustomer.setId(resultSet.getLong("id"));
				lastInsertedCustomer.setLastName(resultSet.getString("last_name"));
				lastInsertedCustomer.setFirstName(resultSet.getString("first_name"));
				lastInsertedCustomer.setAddress(resultSet.getString("address"));
				lastInsertedCustomer.setPhoneNumber(resultSet.getString("phone_number"));
				lastInsertedCustomer.setEmail(resultSet.getString("email"));
				lastInsertedCustomer.setPictureName(resultSet.getString("picture_name"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception("Can not connect to database.", e);
		}
		finally
		{
			if (resultSet != null)
			{
				try
				{
					resultSet.close();
				}
				catch (SQLException ignore)
				{}
			}
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{}
			}
		}
		
		return lastInsertedCustomer;
	}
	
	/**
	 * Returns the last inserted order if orderId is null
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public static Order getOrderFromDb(String orderId) throws Exception {
		
		Order lastInsertedOrder = null;
		
		if (orderId == null)
		{
			orderId = "(SELECT MAX(id) FROM t_order)";
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			throw new Exception("Database driver can not be found in classpath.", e);
		}
		
		String query = "SELECT cust.id, cust.last_name, cust.first_name, cust.address, cust.phone_number, cust.email, cust.picture_name, "
				+ "ord.id, ord.order_date, ord.amount, ord.payment_method, ord.payment_status, ord.shipping_mode, ord.delivery_status "
				+ "FROM t_order ord "
				+ "JOIN t_customer cust ON cust.id = ord.customer_id "
				+ "WHERE ord.id = " + orderId;
		String url = "jdbc:mysql://localhost:3306/sdzee";
		String user = "java";
		String password = "SdZ_eE";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next())
			{
				lastInsertedOrder = new Order();
				Customer customer = new Customer();
				
				customer.setId(resultSet.getLong("cust.id"));
				customer.setLastName(resultSet.getString("cust.last_name"));
				customer.setFirstName(resultSet.getString("cust.first_name"));
				customer.setAddress(resultSet.getString("cust.address"));
				customer.setPhoneNumber(resultSet.getString("cust.phone_number"));
				customer.setEmail(resultSet.getString("cust.email"));
				customer.setPictureName(resultSet.getString("cust.picture_name"));
				
				lastInsertedOrder.setCustomer(customer);
				lastInsertedOrder.setId(resultSet.getLong("ord.id"));
				lastInsertedOrder.setDate(resultSet.getTimestamp("ord.order_date"));
				lastInsertedOrder.setAmount(resultSet.getDouble("ord.amount"));
				lastInsertedOrder.setPaymentMethod(resultSet.getString("ord.payment_method"));
				lastInsertedOrder.setPaymentStatus(resultSet.getString("ord.payment_status"));
				lastInsertedOrder.setShippingMode(resultSet.getString("ord.shipping_mode"));
				lastInsertedOrder.setDeliveryStatus(resultSet.getString("ord.delivery_status"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception("Can not connect to database.", e);
		}
		finally
		{
			if (resultSet != null)
			{
				try
				{
					resultSet.close();
				}
				catch (SQLException ignore)
				{}
			}
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{}
			}
		}
		
		return lastInsertedOrder;
	}

}
