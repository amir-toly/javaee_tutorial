package com.sdzee.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.sdzee.beans.Customer;
import com.sdzee.forms.base.BaseForm;

import eu.medsea.mimeutil.MimeUtil;

public final class CustomerForm extends BaseForm {
	
	private static final String PARAM_LAST_NAME = "customerLastName";
	private static final String PARAM_FIRST_NAME = "customerFirstName";
	private static final String PARAM_ADDRESS = "customerAddress";
	private static final String PARAM_PHONE_NUMBER = "customerPhoneNumber";
	private static final String PARAM_EMAIL_ADDRESS = "customerEmailAddress";
	private static final String PARAM_PICTURE_FILE = "customerPictureFile";
	
	private static final int BUFFER_SIZE = 10240;
	
	public Customer createCustomer(HttpServletRequest request, String path) {
		
		String lastName = getParamValue(request, PARAM_LAST_NAME);
		String firstName = getParamValue(request, PARAM_FIRST_NAME);
		String address = getParamValue(request, PARAM_ADDRESS);
		String phoneNumber = getParamValue(request, PARAM_PHONE_NUMBER);
		String emailAddress = getParamValue(request, PARAM_EMAIL_ADDRESS);
		String pictureName = null;
		
		Customer customer = new Customer();
		
		try
		{
			validateLastName(lastName);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_LAST_NAME, fve.getMessage());
		}
		customer.setLastName(lastName);
		
		try
		{
			validateFirstName(firstName);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_FIRST_NAME, fve.getMessage());
		}
		customer.setFirstName(firstName);
		
		try
		{
			validateAddress(address);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_ADDRESS, fve.getMessage());
		}
		customer.setAddress(address);
		
		try
		{
			validatePhoneNumber(phoneNumber);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_PHONE_NUMBER, fve.getMessage());
		}
		customer.setPhoneNumber(phoneNumber);
		
		try
		{
			validateEmail(emailAddress);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_EMAIL_ADDRESS, fve.getMessage());
		}
		customer.setEmail(emailAddress);
		
		try
		{
			pictureName = validatePictureFile(request, path);
		}
		catch (FormValidationException fve)
		{
			setError(PARAM_PICTURE_FILE, fve.getMessage());
		}
		customer.setPictureName(pictureName);
		
		if (errors.isEmpty())
		{
			result = "Customer created successfully!";
		}
		else
		{
			result = "Customer not created.";
		}
		
		return customer;
	}
	
	/**
	 * VALIDATION METHODS
	 */
	
	private void validateLastName(String lastName) throws FormValidationException {
		
		validateTwoCharactersLongField(lastName, "last name");
	}
	
	private void validateFirstName(String firstName) throws FormValidationException {
		
		if (firstName != null)
		{
			validateTwoCharactersLongField(firstName, "first name");
		}
	}
	
	private void validateAddress(String address) throws FormValidationException {
		
		if (address == null || address.length() < 10)
		{
			throw new FormValidationException("The address must contain at least 10 characters.");
		}
	}
	
	private void validatePhoneNumber(String phoneNumber) throws FormValidationException {
		
		if (phoneNumber == null || !phoneNumber.matches("\\d{4,}"))
		{
			throw new FormValidationException("The phone number must contain numbers (at least 4) only.");
		}
	}
	
	private void validateEmail(String email) throws FormValidationException {
		
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
		{
			throw new FormValidationException("The email address must be valid.");
		}
	}
	
	private String validatePictureFile(HttpServletRequest request, String path) throws FormValidationException {
		
		String filename = null;
		InputStream fileContent = null;
		
		try
		{
			Part part = request.getPart(PARAM_PICTURE_FILE);
			
			if (part != null)
			{
				filename = getFilename(part);
			}
			
			if (filename != null && !filename.isEmpty())
			{
				// Get only the filename, not the whole path where the file comes from
				filename = filename
						.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1);
				
				fileContent = part.getInputStream();
				
				MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
				Collection<?> mimeTypes = MimeUtil.getMimeTypes(fileContent);
				
				if (mimeTypes.toString().startsWith("image"))
				{
					writeFile(fileContent, filename, path);
				}
				else
				{
					throw new FormValidationException("The picture file must match an image type.");
				}
			}
		}
		catch (IllegalStateException ise)
		{
			ise.printStackTrace();
			throw new FormValidationException("The file is too big (1MB max).");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			throw new FormValidationException("Error from the server configuration.");
		}
		catch (ServletException se)
		{
			se.printStackTrace();
			throw new FormValidationException("Request not supported. Expected: multipart/form-data.");
		}
		
		return filename;
	}
	
	/**
	 * OTHER METHODS
	 */
	
	private String getFilename(Part part) {
		
		for (String contentDisposition : part.getHeader("content-disposition").split(";"))
		{
			if (contentDisposition.trim().startsWith("filename"))
			{
				return contentDisposition
						.substring(contentDisposition.indexOf('=') + 1)
						.trim().replace("\"", "");
			}
		}
		
		return null;
	}
	
	private void writeFile(InputStream fileContent, String filename, String path) throws FormValidationException {
		
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		
		try
		{
			input = new BufferedInputStream(fileContent, BUFFER_SIZE);
			output = new BufferedOutputStream(
					new FileOutputStream(new File(path + filename)),
					BUFFER_SIZE);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			
			while ((length = input.read(buffer)) > 0)
			{
				output.write(buffer, 0, length);
			}
		}
		catch (Exception e)
		{
			throw new FormValidationException("Error while writing file on disk.");
		}
		finally
		{
			try
			{
				output.close();
			}
			catch (IOException ignore)
			{}
			try
			{
				input.close();
			}
			catch (IOException ignore)
			{}
		}
	}
}
