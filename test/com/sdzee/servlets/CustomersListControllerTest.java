package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.beans.Customer;
import com.sdzee.servlets.base.CustomersListControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class CustomersListControllerTest extends CustomersListControllerTestBase {

	@Test
	public void testListIsEmpty() {
		
		Assert.assertEquals("No customers created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}
	
	@Test
	public void testThreeCustomers() throws Exception {
		
		String[] one = new String[] {
				"One",
				"First",
				"1st Avenue",
				"0101010101",
				"",
				""
		};
		
		/**
		 * Shows that we can save two customers with the same last name
		 */
		String[] oneAgain = new String[] {
				"One",
				"Again",
				"1st Avenue",
				"0101010101",
				"",
				""
		};
		
		String[] two = new String[] {
				"Two",
				"",
				"2nd Avenue",
				"0202020202",
				"second.two@mail.com",
				""
		};
		
		String[] three = new String[] {
				"Three",
				"",
				"3rd Avenue",
				"0303030303",
				"",
				"/Users/domanduck/Downloads/delete.png"
		};
		
		insertElement(one);
		Customer oneFromDb = AvoidDuplication.getCustomerFromDb(null);
		
		insertElement(oneAgain);
		Customer oneAgainFromDb = AvoidDuplication.getCustomerFromDb(null);
		
		insertElement(two);
		Customer twoFromDb = AvoidDuplication.getCustomerFromDb(null);
		
		insertElement(three);
		Customer threeFromDb = AvoidDuplication.getCustomerFromDb(null);
		
		driver.get(BASE_URL + "listCustomers");
		
		checkElement(oneFromDb, one);
		checkElement(oneAgainFromDb, oneAgain);
		checkElement(twoFromDb, two);
		checkElement(threeFromDb, three);
	}
	
	@Ignore//TODO(manually for now)
	@Test
	public void testCustomersFromSessionOnly() {
	}

}
