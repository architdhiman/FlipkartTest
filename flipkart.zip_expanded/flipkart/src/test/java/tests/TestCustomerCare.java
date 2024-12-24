package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browser;
import pages.CustomerCarePage;
import pages.HomePage;

public class TestCustomerCare extends Browser {
	
	@Test
	void testValidPin() throws InterruptedException {
		//working
		final Logger logger = LogManager.getLogger(TestPincode.class);
		HomePage home = new HomePage(driver);
		logger.info("opened homepage");
		CustomerCarePage customerCare = new CustomerCarePage(driver);
		home.goToCustomerCare();
		logger.info("opened customer care page");

		Assert.assertTrue(customerCare.verifyCustomerCarePage(),"customer care page did not open");
		
		
		

	}

}
