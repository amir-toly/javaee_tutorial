package com.sdzee.servlets.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class ControllerTestBase {
	
	protected static WebDriver driver;
	protected static final String BASE_URL = "http://localhost:8080/javaee_tutorial/";
	
	@BeforeClass
	public static void setUpClass() {
		
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void tearDownClass() {
		
		driver.quit();
	}
	
	protected void insertElement(String[] fieldNames, String[] elementInputs) {
		
		for (int i = 0; i < elementInputs.length; i++)
		{
			driver.findElement(By.id(fieldNames[i])).sendKeys(elementInputs[i]);
		}
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
}
