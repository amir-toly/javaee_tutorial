package com.sdzee.servlets;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sdzee.servlets.base.FormControllerTestBase;

public class CustomerControllerTest extends FormControllerTestBase {

	private static String ERROR_MSG_LAST_NAME = "The last name must contain at least 2 characters.";
	private static String ERROR_MSG_FIRST_NAME = "The first name must contain at least 2 characters.";
	private static String ERROR_MSG_ADDRESS = "The address must contain at least 10 characters.";
	private static String ERROR_MSG_PHONE_NUMBER = "The phone number must contain numbers (at least 4) only.";
	private static String ERROR_MSG_EMAIL = "The email address must be valid.";
	private static String ERROR_MSG_PICTURE_FILE_TOO_BIG = "The file is too big (1MB max).";
	private static String ERROR_MSG_PICTURE_FILE_REQ_NOT_SUPPORTED = "Request not supported. Expected: multipart/form-data.";
	private static String ERROR_MSG_PICTURE_FILE = "The picture file must match an image type.";
	
	@Before
	public void setUp() {
	
		driver.get(BASE_URL + "createCustomer");
	}
	
	@Test
	public void testLastNameNull() {
		
		testFieldNull("customerLastName", ERROR_MSG_LAST_NAME);
	}
	
	@Test
	public void testLastNameEmpty() {
		
		checkErrorMsg("customerLastName", ERROR_MSG_LAST_NAME);
	}
	
	@Test
	public void testLastNameBlank() {
		
		checkErrorMsgForInput("customerLastName", "  ", ERROR_MSG_LAST_NAME);
	}
	
	@Test
	public void testLastNameNotEnough() {
		
		checkErrorMsgForInput("customerLastName", "L", ERROR_MSG_LAST_NAME);
	}
	
	@Test
	public void testFirstNameNotEnough() {
		
		checkErrorMsgForInput("customerFirstName", "F", ERROR_MSG_FIRST_NAME);
	}
	
	@Test
	public void testAddressNull() {
		
		testFieldNull("customerAddress", ERROR_MSG_ADDRESS);
	}
	
	@Test
	public void testAddressEmpty() {
		
		checkErrorMsg("customerAddress", ERROR_MSG_ADDRESS);
	}
	
	@Test
	public void testAddressBlank() {
		
		checkErrorMsgForInput("customerAddress", "          ", ERROR_MSG_ADDRESS);
	}
	
	@Test
	public void testAddressNotEnough() {
		
		checkErrorMsgForInput("customerAddress", "Address..", ERROR_MSG_ADDRESS);
	}
	
	@Test
	public void testPhoneNumberNull() {
		
		testFieldNull("customerPhoneNumber", ERROR_MSG_PHONE_NUMBER);
	}
	
	@Test
	public void testPhoneNumberEmpty() {
		
		checkErrorMsg("customerPhoneNumber", ERROR_MSG_PHONE_NUMBER);
	}
	
	@Test
	public void testPhoneNumberBlank() {
		
		checkErrorMsgForInput("customerPhoneNumber", "    ", ERROR_MSG_PHONE_NUMBER);
	}
	
	@Test
	public void testPhoneNumberNotEnough() {
		
		checkErrorMsgForInput("customerPhoneNumber", "123", ERROR_MSG_PHONE_NUMBER);
	}
	
	@Test
	public void testPhoneNumberWrongFormat() {
		
		checkErrorMsgForInput("customerPhoneNumber", "123456789O", ERROR_MSG_PHONE_NUMBER);
	}
	
	@Test
	public void testEmailWrongFormat() {
		
		checkErrorMsgForInput("customerEmailAddress", "mail@me", ERROR_MSG_EMAIL);
	}
	
	@Ignore//TODO(fix comparison)
	@Test
	public void testPictureFileWrongType() {
		
		checkErrorMsgForInput("customerPictureFile", "/Users/domanduck/Downloads/jquery.js", ERROR_MSG_PICTURE_FILE);
	}
	
	@Ignore//TODO(fix)
	@Test
	public void testPictureFileTooBig() {
		
		checkErrorMsgForInput("cusomerPictureFile", "/Users/domanduck/Downloads/apache-tomcat-7.0.65.zip", ERROR_MSG_PICTURE_FILE_TOO_BIG);
	}
	
	@Test
	public void testPictureFileRequestNotSupported() {
		
		executeJS("document.getElementsByTagName('form')[0].enctype = ''");
		
		checkErrorMsg("customerPictureFile", ERROR_MSG_PICTURE_FILE_REQ_NOT_SUPPORTED);
	}
	
	@Ignore//TODO(manually for now)
	@Test
	public void testPictureFileSrvConfError() {
	}
	
	@Ignore//TODO(manually for now)
	@Test
	public void testPictureFileWritingError() {
	}
}
