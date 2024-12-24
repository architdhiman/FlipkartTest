package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browser;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ExcelUtils;

public class TestFilter extends Browser {
	//working
	@Test(groups= {"smoke"}, priority = 2)
	public void testInvalidMaxPriceFilter() throws InterruptedException, IOException {
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);

		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("Product2");

			home.searchProduct(item);
			resultPage.filterPriceToLowest();
			Assert.assertTrue(resultPage.isNoResultsWarningDisplayed());
		}
	}

	@Test(groups= {"regression"}, priority = 1)
	public void testStorageFilter() throws InterruptedException, IOException {
		//working
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("Product2");
			home.searchProduct(item);
			resultPage.setStorageTo256();
			Assert.assertFalse(resultPage.checkStorage256(resultPage.getSearchResultText()));
		}
	}
}
