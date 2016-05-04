package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.servlets.base.OrdersListControllerTestBase;

public class OrderDeleteControllerTest extends OrdersListControllerTestBase {

	private String[] coyote = new String[] {
			"Coyote",
			"",
			"Pékin, Chine",
			"0987654321",
			"",
			"",
			"1234.56",
			"Espèces",
			"",
			"ACME Delivery",
			""
	};
	
	@Before
	public void setUp() {
		
		super.deleteAllElements();
		
		insertElement(coyote);
	}
	
	@Test
	public void testDeleteNullKey() {
		
		driver.get(BASE_URL + "deleteOrder");
		
		checkElement(coyote[0], coyote);
	}
	
	@Test
	public void testDeleteEmptyKey() {
		
		driver.get(BASE_URL + "deleteOrder?orderKey");
		
		checkElement(coyote[0], coyote);
	}
	
	@Test
	public void testDeleteNonExistingKey() {
		
		driver.get(BASE_URL + "deleteOrder?orderKey=NonExisting");
		
		checkElement(coyote[0], coyote);
	}
	
	@Test
	public void testDeleteOrderDateKey() {
		
		driver.get(BASE_URL + "listOrders");
		
		String orderDate = driver.findElement(By.xpath("//td[contains(text(), '" + coyote[0] + "')]/../td[2]")).getText();
		
		driver.get(BASE_URL + "deleteOrder?orderKey=" + orderDate);
		
		Assert.assertEquals("No orders created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}

}
