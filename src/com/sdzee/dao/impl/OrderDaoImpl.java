package com.sdzee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sdzee.beans.Order;
import com.sdzee.dao.OrderDao;
import com.sdzee.dao.base.BaseDaoImpl;
import com.sdzee.dao.base.DAOException;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

	private static final String SQL_INSERT = "INSERT INTO t_order(customer_id, order_date, amount, payment_method, payment_status, shipping_mode, delivery_status) " +
			"VALUES (?, NOW(), ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM t_order WHERE id = ?";
	
	private DAOFactory daoFactory;
	
	OrderDaoImpl(DAOFactory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(Order order) throws DAOException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet autoGeneratedValues = null;
		
		try
		{
			connection = daoFactory.getConnection();
			preparedStatement = initializePreparedStatement(connection, SQL_INSERT, true,
					order.getCustomer().getId(),
					order.getAmount(),
					order.getPaymentMethod(),
					order.getPaymentStatus(),
					order.getShippingMode(),
					order.getDeliveryStatus());
			int status = preparedStatement.executeUpdate();
			
			if (status == 0)
			{
				throw new DAOException("Error while creating order. No row inserted.");
			}
			
			autoGeneratedValues = preparedStatement.getGeneratedKeys();
			
			if (autoGeneratedValues.next())
			{
				order.setId(autoGeneratedValues.getLong(1));
			}
			else
			{
				throw new DAOException("Error while creating order in database. No auto-generated identifier returned.");
			}
		}
		catch (SQLException sqle)
		{
			throw new DAOException(sqle);
		}
		finally
		{
			closeSilently(autoGeneratedValues, preparedStatement, connection);
		}
	}
	
	@Override
	public void delete(Long id) throws DAOException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = daoFactory.getConnection();
			preparedStatement = initializePreparedStatement(connection, SQL_DELETE, false, id);
			int status = preparedStatement.executeUpdate();
			
			if (status != 1)
			{
				throw new DAOException("Error while deleting order. No order deleted.");
			}
		}
		catch (SQLException sqle)
		{
			throw new DAOException(sqle);
		}
		finally
		{
			closeSilently(preparedStatement, connection);
		}
	}
}