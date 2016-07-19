package com.sdzee.servlets.base;

import java.util.Arrays;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.sdzee.beans.Order;
import com.sdzee.servlets.testutil.AvoidDuplication;

public abstract class OrdersListControllerTestBase extends ListControllerTestBase {
	
	protected void deleteAllElements() {
		
		driver.get(BASE_URL + "listOrders");
		
		super.deleteAllElements();
	}
	
	protected void insertElement(String[] orderInputs) {
		
		driver.get(BASE_URL + "createOrder");
		
		insertElement(AvoidDuplication.orderFields, orderInputs);
	}
	
	public void checkElement(Object elementFromDb, String[] orderInputs) {
		
		Order orderFromDb = (Order) elementFromDb;
		Long listKey = orderFromDb.getId();
		int tableColumnsIdx = 0;
		
		for (int eltInputsIdx = 0; eltInputsIdx < orderInputs.length; eltInputsIdx++)
		{
			String currentInput = orderInputs[eltInputsIdx];
			String fieldValueFromDb = null;
			
			switch (eltInputsIdx) {
			case 0:
				fieldValueFromDb = orderFromDb.getCustomer().getLastName();
				break;
			case 1:
				fieldValueFromDb = orderFromDb.getCustomer().getFirstName();
				break;
			case 2:
				fieldValueFromDb = orderFromDb.getCustomer().getAddress();
				break;
			case 3:
				fieldValueFromDb = orderFromDb.getCustomer().getPhoneNumber();
				break;
			case 4:
				fieldValueFromDb = orderFromDb.getCustomer().getEmail();
				break;
			case 5: // Picture name => get only the filename
				currentInput = currentInput.substring(currentInput.lastIndexOf('/') + 1);
				
				fieldValueFromDb = orderFromDb.getCustomer().getPictureName();
				break;
			case 6:
				fieldValueFromDb = String.valueOf(orderFromDb.getAmount());
				break;
			case 7:
				fieldValueFromDb = orderFromDb.getPaymentMethod();
				break;
			case 8:
				fieldValueFromDb = orderFromDb.getPaymentStatus();
				break;
			case 9:
				fieldValueFromDb = orderFromDb.getShippingMode();
				break;
			case 10:
				fieldValueFromDb = orderFromDb.getDeliveryStatus();
				break;
			default:
				fieldValueFromDb = null;
			}
			
			// Check against display (i.e. HTTP session)
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
				
				Assert.assertEquals(orderInputs[eltInputsIdx], driver.findElement(
						By.xpath("//td[a[@href='/javaee_tutorial/deleteOrder?orderKey=" + listKey + "']]/../td[" + tableColumnsIdx + "]")
				).getText());
			}
			
			// Check against DB
			Assert.assertTrue(
					fieldValueFromDb == null && currentInput.isEmpty() || // When currentInput is empty, DB should return SQL NULL
					fieldValueFromDb.equals(currentInput)
			);
		}
	}
}
