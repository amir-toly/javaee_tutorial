package com.sdzee.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.sdzee.beans.Customer;
import com.sdzee.servlets.base.CustomersListControllerTestBase;
import com.sdzee.servlets.testutil.AvoidDuplication;

public class DownloadControllerTest extends CustomersListControllerTestBase {

	@Test
	public void testValidLink() throws Exception {
		
		String[] coyote = new String[] {
				"Coyote",
				"",
				"Pékin, Chine",
				"0987654321",
				"",
				"/Users/domanduck/Downloads/delete.png"
		};
		
		insertElement(coyote);
		Customer coyoteFromDb = AvoidDuplication.getCustomerFromDb(null);
		
		driver.get(BASE_URL + "listCustomers");
		
		driver.findElement(By.xpath("//tr[2]/td[6]/a")).click();
		
		Assert.assertEquals(BASE_URL + "files/delete.png", driver.getCurrentUrl());
		
		AvoidDuplication.deleteCustomer(coyoteFromDb.getId());
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
