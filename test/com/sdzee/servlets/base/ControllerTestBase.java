package com.sdzee.servlets.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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
}
