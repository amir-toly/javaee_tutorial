package com.sdzee.servlets;

import org.hamcrest.core.StringEndsWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.servlets.base.FormControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class OrderControllerTest extends FormControllerTestBase {

	private static String ERROR_MSG_NEW_CUSTOMER = "The new customer attribute must be set to yes or no.";
	private static String ERROR_MSG_CUSTOMER_KEY = "The selected customer must come from the list.";
	private static String ERROR_MSG_AMOUNT = "The amount must be a positive decimal number.";
	private static String ERROR_MSG_PAYMENT_METHOD = "The payment method must contain at least 2 characters.";
	private static String ERROR_MSG_PAYMENT_STATUS = "The payment status must contain at least 2 characters.";
	private static String ERROR_MSG_SHIPPING_MODE = "The shipping mode must contain at least 2 characters.";
	private static String ERROR_MSG_DELIVERY_STATUS = "The delivery status must contain at least 2 characters.";
	
	@Before
	public void setUp() {
		
		driver.get(BASE_URL + "createOrder");
	}
	
	@Test
	public void testNewCustomerNull() {
		
		testNewCustomerSetUp();
		
		removeElement("yes");
		removeElement("no");
		
		checkNewCustomerMsgAndNoInputsAvailable();
	}

	@Test
	public void testNewCustomerEmpty() {
		
		testNewCustomerSetUp();
		
		checkNewCustomerMsgAndNoInputsAvailable();
	}
	
	@Test
	public void testNewCustomerYes() {
		
		testNewCustomerSetUp();
		
		driver.findElement(By.id("yes")).click();
		
		Assert.assertFalse(driver.findElement(By.id("existingCustomer")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("newCustomer")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		// Check that Yes is still selected when the form is submitted with errors
		Assert.assertTrue(driver.findElement(By.id("yes")).isSelected());
	}
	
	@Test
	public void testNewCustomerNo() {
		
		testNewCustomerNoSetUp();
		
		Assert.assertTrue(driver.findElement(By.id("existingCustomer")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("newCustomer")).isDisplayed());
		
		driver.findElement(By.name("customerKey")).sendKeys("First One");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		// Check that No and the chosen customer are still selected when the form is submitted with errors
		Assert.assertTrue(driver.findElement(By.id("no")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//option[@value='One']")).isSelected());
	}
	
	@Test
	public void testNewCustomerNoCustomerKeyNull() {
		
		testNewCustomerNoSetUp();
		
		executeJS("document.getElementsByName('customerKey')[0].parentNode.removeChild(document.getElementsByName('customerKey')[0])");
		
		checkCustomerKeyMsg();
	}

	@Test
	public void testNewCustomerNoCustomerKeyEmpty() {
		
		testNewCustomerNoSetUp();
		
		checkCustomerKeyMsg();
	}
	
	@Test
	public void testNewCustomerNoCustomerKeyNonExisting() {
		
		testNewCustomerNoSetUp();
		
		executeJS("document.getElementsByName('customerKey')[0].innerHTML += '<option selected=\"selected\" value=\"nonExistingKey\"></option>'");
		
		checkCustomerKeyMsg();
	}
	
	@Test
	public void testAmountNull() {
		
		testFieldNull("orderAmount", ERROR_MSG_AMOUNT);
	}
	
	@Test
	public void testAmountEmpty() {
		
		checkErrorMsg("orderAmount", ERROR_MSG_AMOUNT);
	}
	
	@Ignore//TODO(fix comparison)
	@Test
	public void testAmountBlank() {
		
		checkErrorMsgForInput("orderAmount", "  ", ERROR_MSG_AMOUNT);
	}
	
	@Ignore//TODO(fix comparison)
	@Test
	public void testAmountWrongFormat() {
		
		checkErrorMsgForInput("orderAmount", "-3.2", ERROR_MSG_AMOUNT);
	}
	
	@Test
	public void testPaymentMethodNull() {
		
		testFieldNull("orderPaymentMethod", ERROR_MSG_PAYMENT_METHOD);
	}
	
	@Test
	public void testPaymentMethodEmpty() {
		
		checkErrorMsg("orderPaymentMethod", ERROR_MSG_PAYMENT_METHOD);
	}
	
	@Test
	public void testPaymentMethodBlank() {
		
		checkErrorMsgForInput("orderPaymentMethod", "  ", ERROR_MSG_PAYMENT_METHOD);
	}
	
	@Test
	public void testPaymentMethodNotEnough() {
		
		checkErrorMsgForInput("orderPaymentMethod", "C", ERROR_MSG_PAYMENT_METHOD);
	}
	
	@Test
	public void testPaymentStatusNotEnough() {
		
		checkErrorMsgForInput("orderPaymentStatus", "C", ERROR_MSG_PAYMENT_STATUS);
	}
	
	@Test
	public void testShippingModeNull() {
		
		testFieldNull("orderShippingMode", ERROR_MSG_SHIPPING_MODE);
	}
	
	@Test
	public void testShippingModeEmpty() {
		
		checkErrorMsg("orderShippingMode", ERROR_MSG_SHIPPING_MODE);
	}
	
	@Test
	public void testShippingModeBlank() {
		
		checkErrorMsgForInput("orderShippingMode", "  ", ERROR_MSG_SHIPPING_MODE);
	}
	
	@Test
	public void testShippingModeNotEnough() {
		
		checkErrorMsgForInput("orderShippingMode", "A", ERROR_MSG_SHIPPING_MODE);
	}
	
	@Test
	public void testDeliveryStatusNotEnough() {
		
		checkErrorMsgForInput("orderDeliveryStatus", "D", ERROR_MSG_DELIVERY_STATUS);
	}
	
	@Test
	public void testDisplayPage() {
		
		String[] elementInputs = new String[] {
				"Martin",
				"Pierre",
				"6 avenue du Parc, Lyon",
				"0488776655",
				"pmartin@mail.com",
				"/Users/domanduck/Downloads/delete.png",
				"1234.56",
				"Credit card",
				"Confirmed",
				"UPS",
				"Delivered"
		};
		
		insertElement(AvoidDuplication.orderFields, elementInputs);
		
		int htmlParagraphsIdx = 2; // The first paragraph we want to check is the 3rd one
		
		for (int elementInputsIdx = 0; elementInputsIdx < elementInputs.length; elementInputsIdx++)
		{
			String currentInput = elementInputs[elementInputsIdx];
			
			if (elementInputsIdx == 5) // Picture name => get only the filename
			{
				currentInput = currentInput.substring(currentInput.lastIndexOf('/') + 1);
			}
			
			if (htmlParagraphsIdx == 8) // We want to skip the 9th ('Order' text) and 10th (date field) paragraphs
			{
				htmlParagraphsIdx = 11;
			}
			else
			{
				htmlParagraphsIdx++;
			}
			
			Assert.assertThat(driver.findElement(By.xpath("//div[@id='content']/p[" + htmlParagraphsIdx + "]")).getText(), StringEndsWith.endsWith(currentInput));
		}
	}
	
	/**
	 * Creates a customer in session
	 */
	private void testNewCustomerSetUp() {
		
		String[] one = new String[] {
				"One",
				"First",
				"1st Avenue",
				"0101010101",
				"",
				""
		};
		
		driver.get(BASE_URL + "createCustomer");
		
		insertElement(AvoidDuplication.customerFields, one);
		
		driver.get(BASE_URL + "createOrder");
	}
	
	/**
	 * Creates a customer in session and do something with it
	 */
	private void testNewCustomerNoSetUp() {
		
		testNewCustomerSetUp();
		
		driver.findElement(By.id("no")).click();
	}
	
	private void checkNewCustomerMsgAndNoInputsAvailable() {
		
		checkErrorMsg("no", ERROR_MSG_NEW_CUSTOMER);
		
		Assert.assertFalse(driver.findElement(By.id("existingCustomer")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("newCustomer")).isDisplayed());
	}
	
	private void checkCustomerKeyMsg() {
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Assert.assertEquals(
				ERROR_MSG_CUSTOMER_KEY,
				driver.findElement(By.xpath("//select[@name='customerKey']/following-sibling::span[@class='error']")).getText());
	}
}
