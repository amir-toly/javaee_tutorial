package com.sdzee.servlets.base;

import org.hamcrest.core.StringEndsWith;
import org.junit.Assert;
import org.openqa.selenium.By;

import com.sdzee.beans.Customer;
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
	
	public void checkElement(Object elementFromDb, String[] customerInputs) {
		
		Customer customerFromDb = (Customer) elementFromDb;
		Long listKey = customerFromDb.getId();
		
		for (int i = 0; i < customerInputs.length; i++)
		{
			String currentInput = customerInputs[i];
			String fieldValueFromDb = null;
			String xpathExpForCurrentTd = "//td[a[" + AvoidDuplication.xpathEndsWithAlternative("@href", "?customerKey=", listKey) + "]]/../td[" + (i + 1) + "]";
			
			switch (i) {
			case 0:
				fieldValueFromDb = customerFromDb.getLastName();
				break;
			case 1:
				fieldValueFromDb = customerFromDb.getFirstName();
				break;
			case 2:
				fieldValueFromDb = customerFromDb.getAddress();
				break;
			case 3:
				fieldValueFromDb = customerFromDb.getPhoneNumber();
				break;
			case 4:
				fieldValueFromDb = customerFromDb.getEmail();
				break;
			case 5:
				fieldValueFromDb = customerFromDb.getPictureName();
				break;
			default:
				fieldValueFromDb = null;
			}
			
			// Check against display (i.e. HTTP session)
			if (!currentInput.isEmpty() && i == 5) // Picture name => get only the filename
			{
				currentInput = currentInput.substring(currentInput.lastIndexOf('/') + 1);
				
				Assert.assertEquals("Here", driver.findElement(By.xpath(xpathExpForCurrentTd + "/a")).getText());
				Assert.assertThat(
						driver.findElement(By.xpath(xpathExpForCurrentTd + "/a")).getAttribute("href"),
						StringEndsWith.endsWith(currentInput)
				);
			}
			else
			{
				Assert.assertEquals(currentInput, driver.findElement(By.xpath(xpathExpForCurrentTd)).getText());
			}
			
			// Check against DB
			Assert.assertTrue(
					fieldValueFromDb == null && currentInput.isEmpty() || // When currentInput is empty, DB should return SQL NULL
					fieldValueFromDb.equals(currentInput)
			);
		}
	}
}
