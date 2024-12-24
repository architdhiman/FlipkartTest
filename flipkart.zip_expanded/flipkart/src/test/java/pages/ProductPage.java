package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	WebDriverWait wait;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	public void fillPincode(String pincode) throws InterruptedException {
		WebElement pinBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pincodeInputId']")));
		pinBox.sendKeys(pincode);
		pinBox.submit();
		Thread.sleep(2000);
	}

	public boolean checkPinAvailability() {
		try {
			WebElement validPinMessage = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='hVvnXm' and contains(text(),'Delivery')]")));
			return validPinMessage.isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkInvalidPinWarning() {
		try {
			WebElement invalidPinWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='nyRpc8' and contains(text(),'Not a valid pincode')]")));
			return invalidPinWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkUnavailablePinMessage() {
		try {
			WebElement unavailablePinWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='nyRpc8' and contains(text(),'Currently out of stock in this area.')]")));
			return unavailablePinWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	@FindBy(xpath = "//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	public WebElement addToCartButton;

	public void addToCart() {
		addToCartButton.click();
	}

	@FindBy(xpath = "//div[contains(@class, 'XQDdHH') and contains(@class, 'uuhqql')]")
	public WebElement ProdRating;

	public String getProductRating() {
	    try {
	        WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class, 'XQDdHH') and contains(@class, 'uuhqql')]")));
	        String ratingText = ratingElement.getText();
	        if (ratingText.isEmpty()) {
	            System.out.println("Rating element is visible but has no text.");
	        }
	        return ratingText;
	    } catch (NoSuchElementException e) {
	        System.err.println("Product rating element not found.");
	        return null;
	    }
	}

}
