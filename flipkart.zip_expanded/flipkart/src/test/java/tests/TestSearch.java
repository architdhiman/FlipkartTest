package tests;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import org.testng.annotations.Test;

import base.Browser;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ExcelUtils;

public class TestSearch extends Browser {

	@Test
	public void validProductSearch() throws Exception {
		//working
		final Logger logger = LogManager.getLogger(TestSearch.class);
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String ProductName = row.get("ProductName");
			String expectedName = row.get("expectedName");

			home.searchProduct(ProductName);
			String actualResult = resultPage.getSearchResultText();

        logger.info("Product Details: " + expectedName);
			Assert.assertTrue(actualResult.toLowerCase().contains(expectedName.toLowerCase()),
					"Actual result: " + actualResult + " Expected result: " + expectedName);
		}
	}

	@Test
	void invalidProductSearch() {
		//working
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);

		home.searchProduct("@#$$");

		Assert.assertFalse(resultPage.isResultVisible());
	}

}
