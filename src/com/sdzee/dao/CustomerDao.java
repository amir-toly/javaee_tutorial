package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.Customer;
import com.sdzee.dao.base.DAOException;

public interface CustomerDao {

	void create(Customer customer) throws DAOException;
	void delete(Long id) throws DAOException;
	Customer findById(Long id) throws DAOException;
	Customer findByEmail(String email) throws DAOException;
	List<Customer> findAll() throws DAOException;
}
