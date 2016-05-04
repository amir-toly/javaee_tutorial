package com.sdzee.servlets.base;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public abstract class FormControllerTestBase extends ControllerTestBase {
	
	protected void checkErrorMsgForInput(String elementId, String inputValue, String errorMsg) {
		
		driver.findElement(By.id(elementId)).sendKeys(inputValue);
		
		checkErrorMsg(elementId, errorMsg);
		
		Assert.assertEquals(inputValue.trim(), driver.findElement(By.id(elementId)).getAttribute("value"));
	}
	
	protected void checkErrorMsg(String elementId, String errorMsg) {
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Assert.assertEquals(
				errorMsg,
				driver.findElement(By.xpath("//input[@id='" + elementId + "']/following-sibling::span[@class='error']")).getText());
	}
	
	protected void removeElement(String elementId) {
		
		executeJS("document.getElementById('" + elementId + "').parentNode.removeChild(document.getElementById('" + elementId +"'))");
	}
	
	protected void testFieldNull(String elementId, String errorMsg) {
		
		removeElement(elementId);
		
		// Checking that no NullPointerException prevent the message to be displayed
		checkErrorMsg(elementId, errorMsg);
	}
	
	protected void executeJS(String jsCode) {
		
		((JavascriptExecutor) driver).executeScript(jsCode);
	}
}
