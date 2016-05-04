package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.servlets.base.CustomersListControllerTestBase;

public class DownloadControllerTest extends CustomersListControllerTestBase {

	@Test
	public void testValidLink() {
		
		String[] coyote = new String[] {
				"Coyote",
				"",
				"Pékin, Chine",
				"0987654321",
				"",
				"/Users/domanduck/Downloads/delete.png"
		};
		
		insertElement(coyote);
		
		driver.get(BASE_URL + "listCustomers");
		
		driver.findElement(By.xpath("//tr[2]/td[6]/a")).click();
		
		// If we stay on this page, we can assume the link is valid
		Assert.assertEquals("Customers list", driver.getTitle());
	}
	
	@Test
	public void testFileNull() {
		
		driver.get(BASE_URL + "files");
		
		check404();
	}
	
	@Test
	public void testFileEmpty() {
		
		driver.get(BASE_URL + "files/");
		
		check404();
	}
	
	@Test
	public void testFileNonExisting() {
		
		driver.get(BASE_URL + "files/nonExistingFile.ext");
		
		check404();
	}
	
	private void check404() {
		
		//TODO(write a more efficient test once our custom 404 page has been defined)
		Assert.assertEquals(true, driver.getPageSource().contains("404"));
	}

}
