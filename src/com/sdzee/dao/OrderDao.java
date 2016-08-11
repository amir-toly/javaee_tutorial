package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.Order;
import com.sdzee.dao.base.DAOException;

public interface OrderDao {

	void create(Order order) throws DAOException;
	void delete(Long id) throws DAOException;
	List<Order> findAll() throws DAOException;
}
