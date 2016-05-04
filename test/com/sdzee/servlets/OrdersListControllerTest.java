package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.servlets.base.OrdersListControllerTestBase;

public class OrdersListControllerTest extends OrdersListControllerTestBase {

	@Test
	public void testListIsEmpty() {
		
		Assert.assertEquals("No orders created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}
	
	@Test
	public void testTwoOrders() {
		
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
		insertElement(two);
		
		driver.get(BASE_URL + "listOrders");
		
		checkElement(one[0], one);
		checkElement(two[0], two);
	}

}
