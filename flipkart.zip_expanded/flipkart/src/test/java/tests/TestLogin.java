package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browser;
import pages.HomePage;
import pages.LoginPage;

public class TestLogin extends Browser {

	@Test
	void testLogin() {
		//working
		HomePage home = new HomePage(driver);
		LoginPage login = new LoginPage(driver);
		home.openLoginPage();
		login.enterPhoneNumberAndSubmit("9654215484");		
		try {
	        Thread.sleep(15000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		Assert.assertTrue(login.checkSuccessMessage());

	}
	
	
	@Test
	void testInvalidLogin() {
		HomePage home = new HomePage(driver);
		LoginPage login = new LoginPage(driver);
		home.openLoginPage();
		login.enterPhoneNumberAndSubmit("1234567890");
		try {
			Assert.assertTrue(login.checkSuccessMessage());
		} catch (Exception e) {
			System.out.println("invalid login");
		}

	}

}
