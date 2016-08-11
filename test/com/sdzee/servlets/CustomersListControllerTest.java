package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdzee.beans.Customer;
import com.sdzee.beans.Order;
import com.sdzee.servlets.base.CustomersListControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class CustomersListControllerTest extends CustomersListControllerTestBase {

	private static String[] one = new String[] {
			"One",
			"Again",
			"1st Avenue",
			"0101010101",
			"",
			""
	};
	
	@Test
	public void testListIsEmpty() {
		
		checkNoCustomersMsg();
	}

	@Test
	public void testThreeCustomers() throws Exception {
		
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
		
		goToListCustomersPage();
		
		checkElement(oneFromDb, one);
		checkElement(oneAgainFromDb, oneAgain);
		checkElement(twoFromDb, two);
		checkElement(threeFromDb, three);
		
		AvoidDuplication.deleteCustomer(oneFromDb.getId());
		AvoidDuplication.deleteCustomer(oneAgainFromDb.getId());
		AvoidDuplication.deleteCustomer(twoFromDb.getId());
		AvoidDuplication.deleteCustomer(threeFromDb.getId());
	}
	
	@Ignore//TODO(manually for now)
	@Test
	public void testCustomersFromSessionOnly() {
	}
	
	@Test
	public void testDeleteCustomerWithOrder() throws Exception {
		
		String[] orderFromCoyote = new String[] {
				"Coyote",
				"",
				"Pékin, Chine",
				"0987654321",
				"",
				"",
				"1234.56",
				"Cash",
				"",
				"ACME DElivery",
				""
		};
		
		driver.get(BASE_URL + "createOrder");
		
		insertElement(AvoidDuplication.orderFields, orderFromCoyote);
		Order orderFromCoyoteFromDb = AvoidDuplication.getOrderFromDb(null);
		
		AvoidDuplication.deleteCustomer(orderFromCoyoteFromDb.getCustomer().getId());
		
		checkNoCustomersMsg();
		
		// Order no longer needed
		driver.get(BASE_URL + "deleteOrder?orderKey=" + orderFromCoyoteFromDb.getId());
	}
	
	@Test
	public void testCustomersLoadedIntoSessionFromDb() throws Exception {
		
		insertElement(one);
		Customer oneFromDb = AvoidDuplication.getCustomerFromDb(null);
		
		goToListCustomersPage();
		
		checkElement(oneFromDb, one);
		
		driver.quit();
		
		driver = new FirefoxDriver();
		
		goToListCustomersPage();
		
		checkElement(oneFromDb, one);
		
		AvoidDuplication.deleteCustomer(oneFromDb.getId());
	}
	
	private void checkNoCustomersMsg() {
		
		Assert.assertEquals("No customers created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}
	
	private void goToListCustomersPage() {
		
		driver.get(BASE_URL + "listCustomers");
	}

}
