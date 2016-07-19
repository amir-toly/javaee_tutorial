package com.sdzee.dao;

import com.sdzee.beans.Customer;
import com.sdzee.dao.base.DAOException;

public interface CustomerDao {

	void create(Customer customer) throws DAOException;
	void delete(Long id) throws DAOException;
	Customer findByEmail(String email) throws DAOException;
}
