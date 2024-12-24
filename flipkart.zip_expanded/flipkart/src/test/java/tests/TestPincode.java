package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browser;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.ExcelUtils;

public class TestPincode extends Browser {
	@Test
	void testValidPin() throws InterruptedException, IOException {
		//working
		final Logger logger = LogManager.getLogger(TestPincode.class);
		HomePage home = new HomePage(driver);

		SearchResultsPage resultPage = new SearchResultsPage(driver);

		ProductPage product = new ProductPage(driver);

		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("ProductName");
			String pincode = row.get("pincode");
			home.searchProduct(item);
			logger.info("item filled in search");

			resultPage.openProdPage();
			logger.info("search results page opened");

			resultPage.switchTab();
			product.fillPincode(pincode);
			logger.info("filling pincode:" + pincode);

			Assert.assertTrue(product.checkPinAvailability());
		}

	}

	@Test
	void testinValidPin() throws InterruptedException, IOException {
		//working
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		ProductPage product = new ProductPage(driver);

		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("ProductName");
			String pincode = row.get("invalidPin");

			home.searchProduct(item);
			resultPage.openProdPage();
			resultPage.switchTab();
			product.fillPincode(pincode);
			Assert.assertTrue(product.checkInvalidPinWarning());
		}
	}

	@Test
	void testUnavailablePin() throws InterruptedException, IOException {
		//working
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		ProductPage product = new ProductPage(driver);

		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("ProductName");
			String pincode = row.get("unavailablePin");
			home.searchProduct(item);
			resultPage.openProdPage();
			resultPage.switchTab();
			product.fillPincode(pincode);
			Assert.assertTrue(product.checkUnavailablePinMessage());

		}
	}
}
