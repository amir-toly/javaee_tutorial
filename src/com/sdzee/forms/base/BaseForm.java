package com.sdzee.forms.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.forms.FormValidationException;

/**
 * Parent class for forms
 * @author amir-toly
 *
 */
public abstract class BaseForm {
	
	protected String result;
	protected Map<String, String> errors = new HashMap<String, String>();
	
	public String getResult() {
		return result;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	/**
	 * Utility method which returns the parameter value if not empty. Null otherwise. 
	 * @param request
	 * @param paramName
	 * @return String
	 */
	protected String getParamValue(HttpServletRequest request, String paramName) {
		
		String value = request.getParameter(paramName);
		
		if (value == null || value.trim().length() == 0)
		{
			return null;
		}
		else
		{
			return value.trim();
		}
	}
	
	/**
	 * Add an error message to the corresponding field in the map
	 * @param fieldName
	 * @param message
	 */
	protected void setError(String fieldName, String message) {
		
		errors.put(fieldName, message);
	}
	
	protected void validateTwoCharactersLongField(String fieldValue, String fieldLabel) throws FormValidationException {
		
		if (fieldValue == null || fieldValue.length() < 2)
		{
			throw new FormValidationException("The " + fieldLabel + " must contain at least 2 characters.");
		}
	}
}
