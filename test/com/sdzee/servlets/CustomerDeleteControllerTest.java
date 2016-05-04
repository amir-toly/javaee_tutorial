package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.servlets.base.CustomersListControllerTestBase;

public class CustomerDeleteControllerTest extends CustomersListControllerTestBase {

	private String[] coyote = new String[] {
			"Coyote",
			"",
			"Pékin, Chine",
			"0987654321",
			"",
			""
	};
	
	@Before
	public void setUp() {
		
		super.deleteAllElements();
		
		insertElement(coyote);
	}
	
	@Test
	public void testDeleteNullKey() {
		
		driver.get(BASE_URL + "deleteCustomer");
		
		checkElement(coyote[0], coyote);
	}
	
	@Test
	public void testDeleteEmptyKey() {
		
		driver.get(BASE_URL + "deleteCustomer?customerKey");
		
		checkElement(coyote[0], coyote);
	}
	
	@Test
	public void testDeleteNonExistingKey() {
		
		driver.get(BASE_URL + "deleteCustomer?customerKey=NonExisting");
		
		checkElement(coyote[0], coyote);
	}
	
	@Test
	public void testDeleteCoyoteKey() {
		
		driver.get(BASE_URL + "deleteCustomer?customerKey=Coyote");
		
		Assert.assertEquals("No customers created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}

}
