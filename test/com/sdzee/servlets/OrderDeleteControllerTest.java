package com.sdzee.servlets;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.beans.Order;
import com.sdzee.servlets.base.OrdersListControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class OrderDeleteControllerTest extends OrdersListControllerTestBase {

	private String[] coyoteOrder = new String[] {
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
	
	private Order coyoteOrderFromDb = null;
	
	@Before
	public void setUp() throws Exception {
		
		insertElement(coyoteOrder);
		coyoteOrderFromDb = AvoidDuplication.getOrderFromDb(null);
	}
	
	@After
	public void tearDown() {
		
		super.deleteAllElements();
	}
	
	@Test
	public void testDeleteNullKey() {
		
		driver.get(BASE_URL + "deleteOrder");
		
		checkElement(coyoteOrderFromDb, coyoteOrder);
	}
	
	@Test
	public void testDeleteEmptyKey() {
		
		driver.get(BASE_URL + "deleteOrder?orderKey");
		
		checkElement(coyoteOrderFromDb, coyoteOrder);
	}
	
	@Test
	public void testDeleteNonExistingKey() {
		
		driver.get(BASE_URL + "deleteOrder?orderKey=NonExisting");
		
		checkElement(coyoteOrderFromDb, coyoteOrder);
	}
	
	@Test
	public void testDeleteIdAsKey() throws Exception {
		
		driver.get(BASE_URL + "deleteOrder?orderKey=" + coyoteOrderFromDb.getId());
		
		Assert.assertEquals("No orders created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
		Assert.assertNull(AvoidDuplication.getOrderFromDb(coyoteOrderFromDb.getId().toString()));
	}

}
