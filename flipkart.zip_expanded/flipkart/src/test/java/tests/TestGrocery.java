package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browser;
import pages.GroceryPage;
import pages.HomePage;
import utils.ExcelUtils;

public class TestGrocery extends Browser {

	@Test
	public void testGroceryPin() throws InterruptedException, IOException {
		//working
		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String pincode = row.get("pincode");

			HomePage home = new HomePage(driver);
			GroceryPage grocery = new GroceryPage(driver);
			home.goToGrocery();
			grocery.fillGroceryPincode(pincode);
			Thread.sleep(2000);
			Assert.assertTrue(grocery.checkPinStatus(), "pin was not updated");
		}
	}

	@Test
	public void testGrocerySearch() throws InterruptedException, IOException {
		//working
		List<Map<String, String>> testData = ExcelUtils
				.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String item = row.get("groceryItem");
			String pincode = row.get("pincode");

			HomePage home = new HomePage(driver);
			GroceryPage grocery = new GroceryPage(driver);
			home.goToGrocery();
			grocery.fillGroceryPincode(pincode);
			Thread.sleep(2000);
			Assert.assertTrue(grocery.checkPinStatus(), "pin was not updated");

			Assert.assertTrue(grocery.searchGroceryandVerify(item), "search unsuccessful");

		}
	}

}
