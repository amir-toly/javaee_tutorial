package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.beans.Customer;
import com.sdzee.servlets.base.CustomersListControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class CustomerDeleteControllerTest extends CustomersListControllerTestBase {

	private String[] coyote = new String[] {
			"Coyote",
			"",
			"Pékin, Chine",
			"0987654321",
			"",
			""
	};
	
	private Customer coyoteFromDb = null;
	
	@Before
	public void setUp() throws Exception {
		
		super.deleteAllElements();
		
		insertElement(coyote);
		coyoteFromDb = AvoidDuplication.getCustomerFromDb(null);
	}
	
	@Test
	public void testDeleteNullKey() {
		
		driver.get(BASE_URL + "deleteCustomer");
		
		checkElement(coyoteFromDb, coyote);
	}
	
	@Test
	public void testDeleteEmptyKey() {
		
		driver.get(BASE_URL + "deleteCustomer?customerKey");
		
		checkElement(coyoteFromDb, coyote);
	}
	
	@Test
	public void testDeleteNonExistingKey() {
		
		driver.get(BASE_URL + "deleteCustomer?customerKey=NonExisting");
		
		checkElement(coyoteFromDb, coyote);
	}
	
	@Test
	public void testDeleteIdAsKey() throws Exception {
		
		driver.get(BASE_URL + "deleteCustomer?customerKey=" + coyoteFromDb.getId());
		
		Assert.assertEquals("No customers created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
		Assert.assertNull(AvoidDuplication.getCustomerFromDb(coyoteFromDb.getId().toString()));
	}

}
