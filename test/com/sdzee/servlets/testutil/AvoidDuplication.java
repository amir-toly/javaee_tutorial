package com.sdzee.servlets.testutil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AvoidDuplication {
	
	public static String[] customerFields = new String[] {
			"customerLastName",
			"customerFirstName",
			"customerAddress",
			"customerPhoneNumber",
			"customerEmailAddress",
			"customerPictureFile"
	};
	
	public static void insertElement(WebDriver driver, String[] fieldNames, String[] elementInputs) {
		
		for (int i = 0; i < elementInputs.length; i++)
		{
			driver.findElement(By.id(fieldNames[i])).sendKeys(elementInputs[i]);
		}
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

}
