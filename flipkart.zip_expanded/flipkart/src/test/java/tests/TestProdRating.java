package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browser;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.ExcelUtils;

public class TestProdRating extends Browser {

	@Test
	public void testProductRating() throws InterruptedException, IOException {
		//working
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		ProductPage product = new ProductPage(driver);

		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("ProductName");

			home.searchProduct(item);
			resultPage.openProdPage();
			resultPage.switchTab();

			String rating = product.getProductRating();
			Assert.assertTrue(!(rating == null));
			System.out.println(rating);
		}
	}

}
