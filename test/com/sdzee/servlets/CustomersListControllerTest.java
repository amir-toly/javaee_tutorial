package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.servlets.base.CustomersListControllerTestBase;

public class CustomersListControllerTest extends CustomersListControllerTestBase {

	@Test
	public void testListIsEmpty() {
		
		Assert.assertEquals("No customers created.", driver.findElement(By.xpath("//p[@class='error']")).getText());
	}
	
	@Test
	public void testThreeCustomers() {
		
		String[] one = new String[] {
				"One",
				"First",
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
		insertElement(two);
		insertElement(three);
		
		driver.get(BASE_URL + "listCustomers");
		
		checkElement(one[0], one);
		checkElement(two[0], two);
		checkElement(three[0], three);
	}

}
