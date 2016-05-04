package com.sdzee.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.sdzee.beans.Customer;
import com.sdzee.forms.base.BaseForm;

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
		String pictureName = saveFile(request, path);
		
		Customer customer = new Customer();
		
		customer.setPictureName(pictureName);
		
		try
		{
			validateLastName(lastName);
		}
		catch (Exception e)
		{
			setError(PARAM_LAST_NAME, e.getMessage());
		}
		customer.setLastName(lastName);
		
		try
		{
			validateFirstName(firstName);
		}
		catch (Exception e)
		{
			setError(PARAM_FIRST_NAME, e.getMessage());
		}
		customer.setFirstName(firstName);
		
		try
		{
			validateAddress(address);
		}
		catch (Exception e)
		{
			setError(PARAM_ADDRESS, e.getMessage());
		}
		customer.setAddress(address);
		
		try
		{
			validatePhoneNumber(phoneNumber);
		}
		catch (Exception e)
		{
			setError(PARAM_PHONE_NUMBER, e.getMessage());
		}
		customer.setPhoneNumber(phoneNumber);
		
		try
		{
			validateEmail(emailAddress);
		}
		catch (Exception e)
		{
			setError(PARAM_EMAIL_ADDRESS, e.getMessage());
		}
		customer.setEmail(emailAddress);
		
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
	
	private void validateLastName(String lastName) throws Exception {
		
		validateTwoCharactersLongField(lastName, "last name");
	}
	
	private void validateFirstName(String firstName) throws Exception {
		
		if (firstName != null)
		{
			validateTwoCharactersLongField(firstName, "first name");
		}
	}
	
	private void validateAddress(String address) throws Exception {
		
		if (address == null || address.length() < 10)
		{
			throw new Exception("The address must contain at least 10 characters.");
		}
	}
	
	private void validatePhoneNumber(String phoneNumber) throws Exception {
		
		if (phoneNumber == null || !phoneNumber.matches("\\d{4,}"))
		{
			throw new Exception("The phone number must contain numbers (at least 4) only.");
		}
	}
	
	private void validateEmail(String email) throws Exception {
		
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
		{
			throw new Exception("The email address must be valid.");
		}
	}
	
	private void validatePictureFile(String mimeType) throws Exception {
		
		if (mimeType == null || !mimeType.startsWith("image/"))
		{
			throw new Exception("The picture file must match an image type.");
		}
	}
	
	/**
	 * OTHER METHODS
	 */
	
	private String saveFile(HttpServletRequest request, String path) {
		
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
			}
		}
		catch (IllegalStateException ise)
		{
			setError(PARAM_PICTURE_FILE, "The file is too big (1MB max).");
		}
		catch (IOException ioe)
		{
			setError(PARAM_PICTURE_FILE, "Error from the server configuration.");
		}
		catch (ServletException se)
		{
			setError(PARAM_PICTURE_FILE, "Request not supported. Expected: multipart/form-data.");
		}
		
		if (filename != null && fileContent != null) // If there is actually a file, let's proceed!
		{
			if (errors.isEmpty())
			{
				try
				{
					validatePictureFile(request.getServletContext().getMimeType(filename));
				}
				catch (Exception e)
				{
					setError(PARAM_PICTURE_FILE, e.getMessage());
				}
			}
			
			if (errors.isEmpty())
			{
				try
				{
					writeFile(fileContent, filename, path);
				}
				catch (Exception e)
				{
					setError(PARAM_PICTURE_FILE, "Error while writing file on disk.");
				}
			}
		}
		
		return filename;
	}
	
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
	
	private void writeFile(InputStream fileContent, String filename, String path) throws Exception {
		
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
