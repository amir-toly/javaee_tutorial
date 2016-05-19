package com.sdzee.servlets.base;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.sdzee.servlets.testutil.AvoidDuplication;

public abstract class CustomersListControllerTestBase extends ListControllerTestBase {
	
	protected void deleteAllElements() {
		
		driver.get(BASE_URL + "listCustomers");
		
		super.deleteAllElements();
	}
	
	protected void insertElement(String[] customerInputs) {
		
		driver.get(BASE_URL + "createCustomer");
		
		insertElement(AvoidDuplication.customerFields, customerInputs);
	}
	
	public void checkElement(String listKey, String[] customerInputs) {
		
		for (int i = 0; i < 5; i++)
		{
			Assert.assertEquals(customerInputs[i], driver.findElement(By.xpath("//td[contains(text(), '" + listKey + "')]/../td[" + (i + 1) + "]")).getText());
		}
	}
}
