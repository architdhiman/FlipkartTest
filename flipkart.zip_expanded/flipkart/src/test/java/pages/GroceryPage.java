package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroceryPage {

	WebDriver driver;
	WebDriverWait wait;

	public GroceryPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));

	}

	public void fillGroceryPincode(String pincode) {
		WebElement groceryPinbox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pincode']")));
		if (groceryPinbox.isDisplayed()) {
			groceryPinbox.sendKeys(pincode);
			groceryPinbox.submit();
		}

	}

	public boolean checkPinStatus() {
		WebElement groceryPinStatus = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='kifljp']")));

		return groceryPinStatus.getText().contains("Delivery to");
	}

	public boolean searchGroceryandVerify(String item) {
		WebElement grocerySearchbox = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@name='q' and @title='Search grocery products']")));
		grocerySearchbox.sendKeys(item);
		grocerySearchbox.submit();

		WebElement grocerySearchresultTitle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='BUOuZu']/span")));
		return grocerySearchresultTitle.getText().contains(item);

	}

}
