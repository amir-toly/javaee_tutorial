package com.sdzee.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDaoImpl {
	
	protected PreparedStatement initializePreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		
		for (int i = 0; i < objects.length; i++) {
			preparedStatement.setObject(i + 1, objects[i]);
		}
		
		return preparedStatement;
	}
	
	protected void closeSilently(ResultSet resultSet) {
		
		if (resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException sqle)
			{
				System.out.println("Error while closing ResultSet: " + sqle.getMessage());
			}
		}
	}
	
	protected void closeSilently(Statement statement) {
		
		if (statement != null)
		{
			try
			{
				statement.close();
			}
			catch (SQLException sqle)
			{
				System.out.println("Error while closing Statement: " + sqle.getMessage());
			}
		}
	}
	
	protected void closeSilently(Connection connection) {
		
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException sqle)
			{
				System.out.println("Error while closing Connection: " + sqle.getMessage());
			}
		}
	}
	
	protected void closeSilently(Statement statement, Connection connection) {
		
		closeSilently(statement);
		closeSilently(connection);
	}
	
	protected void closeSilently(ResultSet resultSet, Statement statement, Connection connection) {
		
		closeSilently(resultSet);
		closeSilently(statement);
		closeSilently(connection);
	}
}
