package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.beans.Order;
import com.sdzee.servlets.base.OrdersListControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class OrdersListControllerTest extends OrdersListControllerTestBase {

	@Test
	public void testListIsEmpty() {
		
		Assert.assertEquals("No orders created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}
	
	@Test
	public void testTwoOrders() throws Exception {
		
		String[] one = new String[] {
				"One",
				"",
				"1st Avenue",
				"0101010101",
				"",
				"",
				"42.0",
				"Credit card",
				"Confirmed",
				"UPS",
				""
		};
		
		String[] two = new String[] {
				"Two",
				"",
				"2nd Avenue",
				"0202020202",
				"",
				"",
				"1234.56",
				"Check",
				"",
				"FedEx",
				"Delivered"
		};
		
		insertElement(one);
		Order oneFromDb = AvoidDuplication.getOrderFromDb(null);
		
		insertElement(two);
		Order twoFromDb = AvoidDuplication.getOrderFromDb(null);
		
		driver.get(BASE_URL + "listOrders");
		
		checkElement(oneFromDb, one);
		checkElement(twoFromDb, two);
	}
	
	@Ignore//TODO(how to test this?)
	@Test
	public void testTwoOrdersAtTheSameDate() {
		
	}
	
	@Ignore//TODO(manually for now)
	@Test
	public void testOrdersFromSessionOnly() {
	}

}
