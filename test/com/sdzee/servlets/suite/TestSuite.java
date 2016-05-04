package com.sdzee.servlets.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sdzee.servlets.CustomerControllerTest;
import com.sdzee.servlets.CustomerDeleteControllerTest;
import com.sdzee.servlets.CustomersListControllerTest;
import com.sdzee.servlets.DownloadControllerTest;
import com.sdzee.servlets.OrderControllerTest;
import com.sdzee.servlets.OrderDeleteControllerTest;
import com.sdzee.servlets.OrdersListControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CustomerControllerTest.class,
	OrderControllerTest.class,
	CustomersListControllerTest.class,
	OrdersListControllerTest.class,
	CustomerDeleteControllerTest.class,
	OrderDeleteControllerTest.class,
	DownloadControllerTest.class
})
public class TestSuite {

}
