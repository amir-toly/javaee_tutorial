package com.sdzee.servlets.base;

import org.hamcrest.core.StringEndsWith;
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
		
		for (int i = 0; i < customerInputs.length; i++)
		{
			String currentInput = customerInputs[i];
			
			if (!currentInput.isEmpty() && i == 5) // Picture name => get only the filename
			{
				currentInput = currentInput.substring(currentInput.lastIndexOf('/') + 1);
				
				Assert.assertEquals("Here", driver.findElement(By.xpath("//td[contains(text(), '" + listKey + "')]/../td[" + (i + 1) + "]//a")).getText());
				Assert.assertThat(
						driver.findElement(By.xpath("//td[contains(text(), '" + listKey + "')]/../td[" + (i + 1) + "]//a")).getAttribute("href"),
						StringEndsWith.endsWith(currentInput)
				);
			}
			else
			{
				Assert.assertEquals(currentInput, driver.findElement(By.xpath("//td[contains(text(), '" + listKey + "')]/../td[" + (i + 1) + "]")).getText());
			}
		}
	}
}
