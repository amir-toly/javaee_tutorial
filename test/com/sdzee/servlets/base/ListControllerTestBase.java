package com.sdzee.servlets.base;

import java.util.List;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class ListControllerTestBase extends ControllerTestBase implements ICheckListElement {
	
	@Before
	public void setUp() throws Exception {
		
		deleteAllElements();
	}
	
	protected void deleteAllElements() {
		
		List<WebElement> deleteButtons = driver.findElements(By.xpath("//tr/td[@class='action']/a"));
		
		while (!deleteButtons.isEmpty())
		{
			deleteButtons.get(0).click();
			
			deleteButtons = driver.findElements(By.xpath("//tr/td[@class='action']/a"));
		}
	}
}
