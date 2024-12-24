package tests;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.Browser;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.ExcelUtils;
import utils.ScreenshotUtils;

public class TestAddToCart extends Browser {
	private ExtentReports extent;
	private ExtentTest logger;

	@BeforeMethod
	public void setUp() {
		ExtentSparkReporter spark = new ExtentSparkReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@Test(priority = 1)
	public void testAddToCart() throws IOException {
//working
		logger = extent.createTest("testAddToCart");
		HomePage home = new HomePage(driver);
		SearchResultsPage resultPage = new SearchResultsPage(driver);
		ProductPage product = new ProductPage(driver);
		List<Map<String, String>> testData = ExcelUtils.getTestData("Sheet1");
		for (Map<String, String> row : testData) {
			String ProductName = row.get("ProductName");

			home.searchProduct(ProductName);
			resultPage.openProdPage();
			resultPage.switchTab();

			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						product.addToCartButton);
				product.addToCartButton.click();

				assertFalse(driver.getCurrentUrl().contains("cart"), "Product was not added to the cart.");
				logger.pass("Product was successfully added to the cart.");
			} catch (Exception e) {
				System.out.println("add to cart failed");
			}
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
			try {
				logger.fail(result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test passed");
		}

		extent.flush();
	}
}
