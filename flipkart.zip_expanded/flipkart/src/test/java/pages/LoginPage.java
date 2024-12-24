package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	public void enterPhoneNumberAndSubmit(String PHnumber) {
		WebElement numberInput = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Request OTP']"));
		numberInput.sendKeys(PHnumber);
		submitButton.click();

	}

	public boolean checkSuccessMessage() {
		try {
			WebElement successMessage = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Account')]")));

			return successMessage.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
