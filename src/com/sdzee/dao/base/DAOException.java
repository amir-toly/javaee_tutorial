package com.sdzee.dao.base;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -8261753600454863777L;

	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DAOException(Throwable cause) {
		super(cause);
	}
}