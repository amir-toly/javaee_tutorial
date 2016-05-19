package com.sdzee.servlets.base;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sdzee.servlets.testutil.AvoidDuplication;

public abstract class OrdersListControllerTestBase extends ListControllerTestBase {
	
	protected void deleteAllElements() {
		
		driver.get(BASE_URL + "listOrders");
		
		super.deleteAllElements();
	}
	
	protected void insertElement(String[] orderInputs) {
		
		driver.get(BASE_URL + "createOrder");
		
		List<WebElement> yesRadioButtons = driver.findElements(By.id("yes"));
		
		if (!yesRadioButtons.isEmpty())
		{
			yesRadioButtons.get(0).click();
		}
		
		insertElement(AvoidDuplication.orderFields, orderInputs);
	}
	
	public void checkElement(String listKey, String[] orderInputs) {
		
		int tableColumnsIdx = 0;
		
		for (int eltInputsIdx = 0; eltInputsIdx < orderInputs.length; eltInputsIdx++)
		{
			// Do not check firstName, address, phoneNumber, email and pictureName fields
			if (!Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }).contains(new Integer(eltInputsIdx)))
			{
				if (tableColumnsIdx == 1) // We want to skip the 2nd column (date field)
				{
					tableColumnsIdx = 3;
				}
				else
				{
					tableColumnsIdx++;
				}
				
				Assert.assertEquals(orderInputs[eltInputsIdx], driver.findElement(By.xpath("//td[contains(text(), '" + listKey + "')]/../td[" + tableColumnsIdx + "]")).getText());
			}
		}
	}
}
