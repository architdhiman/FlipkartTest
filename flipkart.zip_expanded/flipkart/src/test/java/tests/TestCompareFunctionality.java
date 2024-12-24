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

public class TestCompareFunctionality extends Browser {
	@Test(priority = 1)
	void TestCompareFunction() throws IOException {
		//working
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String ProductName = row.get("ProductName");
			home.searchProduct(ProductName);
			Assert.assertTrue(resultPage.isResultVisible());
//			Assert.assertTrue(resultPage.isCompared());

		}

	}
}
