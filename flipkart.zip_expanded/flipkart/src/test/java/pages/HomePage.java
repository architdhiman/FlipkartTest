package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	public void goToCustomerCare() {

		WebElement optionsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='_1TOQfO'])[2]")));
		optionsButton.click();
		WebElement helpcentreOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/helpcentre']")));
		helpcentreOption.click();

	}

	public void goToNotification() {
		driver.manage().window().fullscreen();
		WebElement optionsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='H6-NpN'])")));
		
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(optionsButton)).click();
		WebElement notificationOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@href='/communication-preferences/push?t=all']")));
		notificationOption.click();

	}

	public void openLoginPage() {
		WebElement loginButton = driver.findElement(By.xpath("//a[@title='Login']"));
		loginButton.click();

	}

	@FindBy(xpath = "//input[@name='pincode']")
	WebElement groceryPinbox;

	public void fillGroceryPincode(String pincode) {
		if (groceryPinbox.isDisplayed()) {
			groceryPinbox.sendKeys(pincode);
		}

	}

	public void goToGrocery() {
		WebElement groceryTile = driver.findElement(By.xpath("//a[@aria-label='Grocery']"));
		groceryTile.click();
	}

	public void searchProduct(String productName) {

		WebElement searchBox = driver.findElement(By.xpath("//input[@class='Pke_EE']")); // Update with actual locator
		searchBox.sendKeys(productName);
		WebElement searchButton = driver.findElement(By.xpath("//*[@class='_2iLD__']"));
		searchButton.click();
	}

	public String getSearchResultText() {
		WebElement resultElement = driver.findElement(By.xpath("//div[@class='KzDlHZ']")); // Update with actual locator
		return resultElement.getText();
	}

	public void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public boolean checkTwtHandle() {
		WebElement TwtIcon = driver.findElement(By.xpath("//a[@aria-label='Twitter']"));
		TwtIcon.click();

		wait.until(ExpectedConditions.urlContains("x"));
		return driver.getCurrentUrl().equalsIgnoreCase("https://x.com/flipkart");
	}

	public boolean checkFBHandle() {
		WebElement TwtIcon = driver.findElement(By.xpath("//a[@aria-label='Facebook']"));
		TwtIcon.click();

		wait.until(ExpectedConditions.urlContains("facebook"));
		return driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/flipkart");
	}

	public boolean checkYTHandle() {
		WebElement TwtIcon = driver.findElement(By.xpath("//a[@aria-label='YouTube']"));
		TwtIcon.click();

		wait.until(ExpectedConditions.urlContains("youtube"));
		return driver.getCurrentUrl().equalsIgnoreCase("https://www.youtube.com/flipkart");
	}

}
