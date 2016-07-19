package com.sdzee.dao.base;

public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -2972809858332730767L;

	public DAOConfigurationException(String message) {
		super(message);
	}
	
	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}
}
